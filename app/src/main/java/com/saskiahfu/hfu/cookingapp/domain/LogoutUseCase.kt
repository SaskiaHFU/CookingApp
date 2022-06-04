package com.saskiahfu.hfu.cookingapp.domain

import com.saskiahfu.hfu.cookingapp.data.LoginState
import com.saskiahfu.hfu.cookingapp.data.ProductsRepository
import com.saskiahfu.hfu.cookingapp.data.ShoppingCartRepository
import com.saskiahfu.hfu.cookingapp.data.UserSettingsRepository
import com.saskiahfu.hfu.cookingapp.domain.model.ShoppingCartId
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LogoutUseCase @Inject constructor(
    private val userSettingsRepository: UserSettingsRepository,
    private val productsRepository: ProductsRepository,
    private val shoppingCartRepository: ShoppingCartRepository,
) {

    suspend operator fun invoke() = withContext(Dispatchers.Default) {
        userSettingsRepository.updateSettings {
            it.copy(
                loginState = LoginState.LoggedOut,
                cartId = ShoppingCartId(""),
            )
        }

        productsRepository.deleteAll()
        shoppingCartRepository.deleteAll()
    }
}
