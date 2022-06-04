package com.saskiahfu.hfu.cookingapp.domain

import com.saskiahfu.hfu.cookingapp.data.LoginState
import com.saskiahfu.hfu.cookingapp.data.UserSettingsRepository
import com.saskiahfu.hfu.cookingapp.data.network.SignUpRequestDto
import com.saskiahfu.hfu.cookingapp.data.network.WebService
import com.saskiahfu.hfu.cookingapp.domain.downloadUseCase.DownloadProductsUseCase
import com.saskiahfu.hfu.cookingapp.domain.model.ShoppingCartId
import java.util.Base64
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SignUpUseCase @Inject constructor(
    private val webService: WebService,
    private val userSettingsRepository: UserSettingsRepository,
    private val downloadProductsUseCase: DownloadProductsUseCase,
) {
    suspend operator fun invoke(username: String, password: String) =
        withContext(Dispatchers.Default) {
            val credentials =
                Base64.getEncoder().encodeToString("$username:$password".toByteArray())
            userSettingsRepository.updateSettings {
                it.copy(
                    loginState = LoginState.LoggingIn(
                        credentials
                    )
                )
            }

            val response = webService.signUp(
                SignUpRequestDto(username, password)
            )
            userSettingsRepository.updateSettings {
                it.copy(
                    cartId = ShoppingCartId(response.cartId),
                )
            }

            downloadProductsUseCase()

            userSettingsRepository.updateSettings {
                it.copy(
                    loginState = LoginState.LoggedIn(
                        credentials
                    )
                )
            }
        }
}
