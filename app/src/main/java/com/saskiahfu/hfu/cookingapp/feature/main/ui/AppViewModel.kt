package com.saskiahfu.hfu.cookingapp.feature.main.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.saskiahfu.hfu.cookingapp.data.UserSettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.map

@HiltViewModel
class AppViewModel @Inject constructor(
    private val userSettingsRepository: UserSettingsRepository,
) : ViewModel() {

    fun isLoggedIn() = userSettingsRepository
        .observeSettings()
        .map { it.loginState }
        .asLiveData()
}
