package com.saskiahfu.hfu.cookingapp.domain

import android.content.Context
import com.saskiahfu.hfu.cookingapp.data.LoginState
import com.saskiahfu.hfu.cookingapp.data.UserSettingsRepository
import com.saskiahfu.hfu.cookingapp.data.network.WebService
import com.saskiahfu.hfu.cookingapp.domain.downloadUseCase.*
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Base64
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginUseCase @Inject constructor(
    private val webService: WebService,
    private val userSettingsRepository: UserSettingsRepository,
    private val downloadCartUseCase: DownloadCartUseCase,
    private val downloadMealsUseCase: DownloadMealsUseCase,
    private val downloadRecipesUseCase: DownloadRecipesUseCase,
    private val downloadRecipeCategoriesUseCase: DownloadRecipeCategoriesUseCase,
    @ApplicationContext private val context: Context,
) {

    suspend operator fun invoke(
        username: String,
        password: String,
    ) = withContext(Dispatchers.Default) {
        val credentials = Base64.getEncoder().encodeToString("$username:$password".toByteArray())
        userSettingsRepository.updateSettings {
            it.copy(
                loginState = LoginState.LoggingIn(credentials),
            )
        }

        webService.login()

        userSettingsRepository.updateSettings {
            it.copy(
            )
        }

        downloadCartUseCase()
        downloadMealsUseCase()
        downloadRecipesUseCase()
        downloadRecipeCategoriesUseCase


        userSettingsRepository.updateSettings {
            it.copy(
                loginState = LoginState.LoggedIn(credentials),
            )
        }
    }
}
