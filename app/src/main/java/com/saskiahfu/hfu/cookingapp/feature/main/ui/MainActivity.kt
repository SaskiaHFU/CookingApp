package com.saskiahfu.hfu.cookingapp.feature.main.ui


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.saskiahfu.hfu.cookingapp.data.LoginState
import com.saskiahfu.hfu.cookingapp.feature.login.ui.LoginScreen
import com.saskiahfu.hfu.cookingapp.feature.login.ui.SignUpScreen
import com.saskiahfu.hfu.cookingapp.ui.theme.CookingAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val loginState by viewModel.isLoggedIn().observeAsState()

            CookingAppTheme() {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    if (loginState is LoginState.LoggedIn) {
                        MainScreen()
                    } else if (loginState != null) {

//                       LoginScreen()
//                       SignUpScreen()
                        MainScreen()

//                        if (loginState is LoginState.LoggingIn) {
//                            CircularProgressIndicator()
//                        }
                    }
                }
            }
        }
    }
}
