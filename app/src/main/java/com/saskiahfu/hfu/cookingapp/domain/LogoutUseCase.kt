package com.saskiahfu.hfu.cookingapp.domain

import com.saskiahfu.hfu.cookingapp.data.LoginState
import com.saskiahfu.hfu.cookingapp.data.UserSettingsRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LogoutUseCase @Inject constructor(
    private val userSettingsRepository: UserSettingsRepository,
) {
    suspend operator fun invoke() = withContext(Dispatchers.Default) {
        userSettingsRepository.updateSettings {
            it.copy(
                loginState = LoginState.LoggedOut,
            )
        }
    }
}
