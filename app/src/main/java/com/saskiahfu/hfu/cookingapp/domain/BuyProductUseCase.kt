package com.saskiahfu.hfu.cookingapp.domain

//import com.saskiahfu.hfu.cookingapp.data.ProductsRepository
//import com.saskiahfu.hfu.cookingapp.data.ShoppingCartRepository
//import com.saskiahfu.hfu.cookingapp.data.UserSettingsRepository
//import com.saskiahfu.hfu.cookingapp.data.network.AddItemRequestDto
//import com.saskiahfu.hfu.cookingapp.data.network.WebService
//import com.saskiahfu.hfu.cookingapp.domain.model.ProductId
//import com.saskiahfu.hfu.cookingapp.domain.model.addProduct
//import javax.inject.Inject
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//
//class BuyProductUseCase @Inject constructor(
//    private val webService: WebService,
//    private val userSettingsRepository: UserSettingsRepository,
//    private val productsRepository: ProductsRepository,
//    private val shoppingCartRepository: ShoppingCartRepository,
//) {
//
//    suspend operator fun invoke(productId: ProductId): Boolean = withContext(Dispatchers.Default) {
//        val product = productsRepository.getBuyableProductById(productId)
//        if (product == null) {
//            return@withContext false
//        }
//
//        val cart = shoppingCartRepository.getCurrentCart()
//        val updatedCart = addProduct(cart, product)
//        shoppingCartRepository.updateCart(updatedCart)
//
//        webService.addItem(
//            userSettingsRepository.getSettings().cartId.value,
//            AddItemRequestDto(productId.value),
//        )
//
//        return@withContext true
//    }
//}
