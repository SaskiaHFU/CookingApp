package com.saskiahfu.hfu.cookingapp.domain

import android.util.Log
import com.saskiahfu.hfu.cookingapp.data.CartRepository
import com.saskiahfu.hfu.cookingapp.data.network.WebService
import com.saskiahfu.hfu.cookingapp.domain.model.CartItemId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteCartItemUseCase @Inject constructor(
    private val webService: WebService,
    private val cartRepository: CartRepository,
) {

    suspend operator fun invoke(id: CartItemId) = withContext(Dispatchers.Default) {
        cartRepository.deleteById(id)
    }
}