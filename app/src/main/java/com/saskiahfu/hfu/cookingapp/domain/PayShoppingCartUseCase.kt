package com.saskiahfu.hfu.cookingapp.domain

//import com.saskiahfu.hfu.cookingapp.data.ShoppingCartRepository
//import com.saskiahfu.hfu.cookingapp.data.UserSettingsRepository
//import com.saskiahfu.hfu.cookingapp.data.network.WebService
//import com.saskiahfu.hfu.cookingapp.domain.model.removeAllProducts
//import javax.inject.Inject
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//
//class PayShoppingCartUseCase @Inject constructor(
//    private val webService: WebService,
//    private val userSettingsRepository: UserSettingsRepository,
//    private val shoppingCartRepository: ShoppingCartRepository,
//) {
//
//    suspend operator fun invoke(): Boolean = withContext(Dispatchers.Default) {
//        val cart = shoppingCartRepository.getCurrentCart()
//        val updatedCart = removeAllProducts(cart)
//        shoppingCartRepository.updateCart(updatedCart)
//
//        webService.clearCart(
//            userSettingsRepository.getSettings().cartId.value
//        )
//
//        true
//    }
//}
