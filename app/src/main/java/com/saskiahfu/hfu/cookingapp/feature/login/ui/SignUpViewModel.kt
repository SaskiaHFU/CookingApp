package com.saskiahfu.hfu.cookingapp.feature.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saskiahfu.hfu.cookingapp.domain.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUp: SignUpUseCase,
) : ViewModel() {

    fun onSignUp(username: String, password: String) {
        viewModelScope.launch {
            signUp(username, password)
        }
    }
}