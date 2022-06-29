package com.saskiahfu.hfu.cookingapp.feature.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import com.saskiahfu.hfu.cookingapp.feature.main.navigation.BottomNavigationItem
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.saskiahfu.hfu.cookingapp.R
import com.saskiahfu.hfu.cookingapp.feature.main.contentPadding
import com.saskiahfu.hfu.cookingapp.feature.main.modifier

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(),
) {
    LoginScreenUi(viewModel::onLogin)
//        , viewModel::onSignUp)
}

@Composable
private fun LoginScreenUi(
    onLogin: (username: String, password: String) -> Unit,
//    onSignUp: (username: String, password: String) -> Unit,
) {

    val navController = rememberNavController()

    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    val fontSizeSmall = 10.sp
    val regularText = MaterialTheme.typography.body1
    val headings1 = MaterialTheme.typography.h1

    val loginImg = R.drawable.plant_croped

    val startPadding = PaddingValues(
        start = 30.dp,
        top = 215.dp,
        end = 30.dp,

        )

    Column(
        modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        Image(
            painter = painterResource(loginImg),
            contentDescription = "Plant",
            contentScale = ContentScale.Fit,
            alignment = Alignment.TopEnd,
            modifier = Modifier
                .width(360.dp)
                .height(630.dp),
        )
    }

//    Upper Part
    Column() {
        Column(
            modifier
                .padding(startPadding)
        ) {
            Text(
                text = "Welcome",
                style = headings1,
            )
            Text(
                text = "back",
                style = headings1,
            )
        }
//Login
        Column(
            modifier
                .padding(contentPadding)
        ) {
            TextField(
                value = userName,
                onValueChange = { userName = it },
                singleLine = true,
                label = {
                    Text(stringResource(R.string.login_input_email))
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.background
                )
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                label = {
                    Text(stringResource(R.string.login_input_password))
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.background
                )
            )

//Subinfo
            Row(
//                verticalAlignment = Alignment.CenterVertically,
                modifier
                    .fillMaxWidth()
                    .padding(0.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                TextButton(
                    onClick = { },
                ) {
                    Text(
                        stringResource(R.string.login_button_forgot_password),
                        style = regularText,
                        fontSize = fontSizeSmall
                    )
                }

                TextButton(
                    onClick = {
                        navController.navigate(BottomNavigationItem.Signup.routeName)
                    },
                ) {
                    Text(
                        stringResource(R.string.login_button_signup),
                        style = regularText,
                        fontSize = fontSizeSmall
                    )
                }
            }
        }

// Bottom Navbar
        Column(
            modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom,
        ) {
            Row(
                modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = {
                        onLogin(userName, password)
                    },
                    enabled = userName.isNotBlank() && password.isNotBlank(),
                    modifier = Modifier
                        .size(width = 206.dp, height = 65.dp)
                        .clip(shape = RoundedCornerShape(topStart = 30.dp, bottomStart = 30.dp)),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),

                    ) {
                    Text(
                        text = stringResource(R.string.login_button_signin),
                        style = regularText
                    )
                }
            }

        }
    }


}

//@Preview
//@Composable
//fun LoginScreen_Preview() {
//    LoginScreenUi({ _, _ -> }, { _, _ -> })
//}
