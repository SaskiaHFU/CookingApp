package com.saskiahfu.hfu.cookingapp.feature.profile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saskiahfu.hfu.cookingapp.domain.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val logout: LogoutUseCase,
) : ViewModel() {

    fun onLogout() {
        viewModelScope.launch {
            logout()
        }
    }
}
