package com.saskiahfu.hfu.cookingapp.domain.downloadUseCase

import com.saskiahfu.hfu.cookingapp.data.ProductsRepository
import com.saskiahfu.hfu.cookingapp.data.network.WebService
import com.saskiahfu.hfu.cookingapp.domain.model.BuyableProduct
import com.saskiahfu.hfu.cookingapp.domain.model.Product
import com.saskiahfu.hfu.cookingapp.domain.model.ProductIcon
import com.saskiahfu.hfu.cookingapp.domain.model.ProductId
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DownloadProductsUseCase @Inject constructor(
    private val webService: WebService,
    private val productsRepository: ProductsRepository,
) {

    suspend operator fun invoke() = withContext(Dispatchers.Default) {
        kotlin.runCatching {
            webService.getProducts().mapNotNull {
                Product.create(
                    ProductId(it.id),
                    it.name,
                    icon = ProductIcon.Remote(it.imageUrl),
                    description = it.description,
                )?.let { product ->
                    BuyableProduct.create(product, it.price)
                }
            }.forEach {
                productsRepository.addBuyableProduct(it)
            }
        }
    }
}
