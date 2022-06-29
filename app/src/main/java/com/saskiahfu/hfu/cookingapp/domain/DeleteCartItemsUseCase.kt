package com.saskiahfu.hfu.cookingapp.domain

import android.util.Log
import com.saskiahfu.hfu.cookingapp.data.CartRepository
import com.saskiahfu.hfu.cookingapp.data.network.WebService
import com.saskiahfu.hfu.cookingapp.domain.model.CartItem
import com.saskiahfu.hfu.cookingapp.domain.model.CartItemId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteCartItemsUseCase @Inject constructor(
    private val webService: WebService,
    private val cartRepository: CartRepository,
) {

    suspend operator fun invoke() = withContext(Dispatchers.Default) {
        kotlin.runCatching {
            webService.getCart().mapNotNull {
                CartItem.create(
                    CartItemId(it.id),
                    it.item
                )
            }.forEach {
                cartRepository.deleteAll()
            }
        }
    }.fold(
        { Log.e("CART DELETE", "ok") },
        { Log.e("CART DELETE", it.toString(), it) },
    )
}