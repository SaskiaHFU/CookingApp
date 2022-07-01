package com.saskiahfu.hfu.cookingapp.domain

import com.saskiahfu.hfu.cookingapp.data.CartRepository
import com.saskiahfu.hfu.cookingapp.data.network.AddCartItemRequestDto
import com.saskiahfu.hfu.cookingapp.data.network.WebService
import com.saskiahfu.hfu.cookingapp.domain.model.CartItem
import com.saskiahfu.hfu.cookingapp.domain.model.CartItemId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class AddItemToCartUseCase @Inject constructor(
    private val webService: WebService,
    private val cartRepository: CartRepository
) {

    suspend operator fun invoke(
        item: String,
    ) =
        withContext(Dispatchers.Default) {

            webService.addCartItem(
                AddCartItemRequestDto(
                    item,
                ),
            )

            CartItem.create(
                CartItemId(UUID.randomUUID().toString()),
                item
            )
                ?.let { cartRepository.addCartItem(it) }

            return@withContext true
        }
}