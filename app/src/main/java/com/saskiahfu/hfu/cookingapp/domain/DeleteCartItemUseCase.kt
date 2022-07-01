package com.saskiahfu.hfu.cookingapp.domain

import android.util.Log
import com.saskiahfu.hfu.cookingapp.data.CartRepository
import com.saskiahfu.hfu.cookingapp.data.network.DeleteCartItemRequestDto
import com.saskiahfu.hfu.cookingapp.data.network.WebService
import com.saskiahfu.hfu.cookingapp.domain.model.CartItemId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteCartItemUseCase @Inject constructor(
    private val webService: WebService,
    private val cartRepository: CartRepository,
) {

    suspend operator fun invoke(id: CartItemId, item: String) = withContext(Dispatchers.Default) {
        kotlin.runCatching {
            cartRepository.deleteById(id)
            webService.clearCartItem(
                id = id.value,
                DeleteCartItemRequestDto(
                    item
                )
            )
        }
    }.fold(
        { Log.e("CART ITEM DELETE", "ok") },
        { Log.e("CART ITEM DELETE", it.toString(), it) },
    )
}