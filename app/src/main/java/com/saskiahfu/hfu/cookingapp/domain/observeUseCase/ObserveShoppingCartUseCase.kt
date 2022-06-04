package com.saskiahfu.hfu.cookingapp.domain.observeUseCase

import com.saskiahfu.hfu.cookingapp.data.ShoppingCartRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class ObserveShoppingCartUseCase @Inject constructor(
    private val shoppingCartRepository: ShoppingCartRepository,
) {
    operator fun invoke() = shoppingCartRepository.observeCurrentCart().flowOn(Dispatchers.Default)
}
