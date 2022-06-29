package com.saskiahfu.hfu.cookingapp.domain.observeUseCase

import com.saskiahfu.hfu.cookingapp.data.CartRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn


class ObserveCartUseCase @Inject constructor(
    private val cartRepository: CartRepository,
) {
    operator fun invoke() = cartRepository.observeAllCartItems().flowOn(Dispatchers.Default)
}


//class ObserveShoppingCartUseCase @Inject constructor(
//    private val shoppingCartRepository: ShoppingCartRepository,
//) {
//    operator fun invoke() = shoppingCartRepository.observeCurrentCart().flowOn(Dispatchers.Default)
//}
