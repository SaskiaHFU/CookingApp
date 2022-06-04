package com.saskiahfu.hfu.cookingapp.domain.downloadUseCase

import com.saskiahfu.hfu.cookingapp.data.ProductsRepository
import com.saskiahfu.hfu.cookingapp.data.ShoppingCartRepository
import com.saskiahfu.hfu.cookingapp.data.UserSettingsRepository
import com.saskiahfu.hfu.cookingapp.data.network.WebService
import com.saskiahfu.hfu.cookingapp.domain.model.ProductId
import com.saskiahfu.hfu.cookingapp.domain.model.ShoppingCart
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DownloadShoppingCartUseCase @Inject constructor(
    private val webService: WebService,
    private val userSettingsRepository: UserSettingsRepository,
    private val productsRepository: ProductsRepository,
    private val shoppingCartRepository: ShoppingCartRepository,
) {

    suspend operator fun invoke() = withContext(Dispatchers.Default) {
        kotlin.runCatching {
            val cartId = userSettingsRepository.getSettings().cartId
            val response = webService.getCartById(cartId.value)
            val groups = response.groupingBy {
                it.productId
            }
            val itemAndCount = groups.eachCount()

            if (itemAndCount.isEmpty()) {
                val cart = ShoppingCart.Empty(cartId)
                shoppingCartRepository.updateCart(cart)
            } else {
                val products = itemAndCount.mapKeys { (productId, _) ->
                    productsRepository.getBuyableProductById(ProductId(productId))
                }.filterKeys { it != null }.mapKeys { it.key!! }
                val cart = ShoppingCart.Unpayed.create(
                    cartId,
                    products,
                    products.entries.sumOf { it.key.price * it.value }
                )
                shoppingCartRepository.updateCart(cart)
            }
        }
    }
}
