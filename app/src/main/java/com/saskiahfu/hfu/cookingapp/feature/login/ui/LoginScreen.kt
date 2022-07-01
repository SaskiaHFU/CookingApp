package com.saskiahfu.hfu.cookingapp.feature.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
import com.saskiahfu.hfu.cookingapp.R
import com.saskiahfu.hfu.cookingapp.feature.main.contentPadding
import com.saskiahfu.hfu.cookingapp.feature.main.modifier
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.ui.graphics.Color
import com.saskiahfu.hfu.cookingapp.feature.main.containerPadding
import com.saskiahfu.hfu.cookingapp.feature.main.signInPadding


@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(),
    viewModelS: SignUpViewModel = viewModel(),

    ) {
    LoginScreenUi(viewModel::onLogin, viewModelS::onSignUp)
}

@Composable
private fun LoginScreenUi(
    onLogin: (username: String, password: String) -> Unit,
    onSignUp: (username: String, password: String) -> Unit,
) {

    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var showSignup by remember { mutableStateOf(false) }

    val fontSizeSmall = 10.sp
    val regularText = MaterialTheme.typography.body1
    val headings1 = MaterialTheme.typography.h1

    val loginImg = R.drawable.plant_croped

    val startPadding = PaddingValues(
        start = 30.dp,
        top = 215.dp,
        end = 30.dp,

        )


    if (showSignup) {

        var userNameSignup by remember { mutableStateOf("") }
        var passwordSignup by remember { mutableStateOf("") }
        var passwordRepeatSignup by remember { mutableStateOf("") }
        var errorMessage by remember { mutableStateOf(false) }

//Upper Part
        Box(
            modifier
                .background(MaterialTheme.colors.background)
                .fillMaxSize()
        ) {

            Column(
                modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.background),

                ) {
                Row(
                    modifier
                        .padding(signInPadding)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.signup),
                        style = MaterialTheme.typography.h1,
                    )
                    IconButton(
                        onClick = {
                            showSignup = false
                        },
                    ) {
                        Icon(
                            Icons.Filled.Close, //TODO Change Icon
                            contentDescription = stringResource(R.string.signup_close),
                            modifier.size(50.dp)
                        )
                    }
                }

                Spacer(modifier.height(50.dp))

//Sign Up
                val signupImg = R.drawable.plant_signup

                Column(
                    verticalArrangement = Arrangement.spacedBy(45.dp)
                ) {
                    Column(
                        modifier.padding(containerPadding),
                        verticalArrangement = Arrangement.spacedBy(45.dp)
                    ) {

                        Column(
                            verticalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            TextField(
                                value = userNameSignup,
                                onValueChange = { userNameSignup = it },
                                singleLine = true,
                                placeholder = { Text(stringResource(R.string.signup_input_email)) },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = MaterialTheme.colors.background
                                )
                            )
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            TextField(
                                value = passwordSignup,
                                onValueChange = { passwordSignup = it },
                                singleLine = true,
                                visualTransformation = PasswordVisualTransformation(),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                placeholder = { Text(stringResource(R.string.signup_input_password)) },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = MaterialTheme.colors.background
                                )
                            )
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            TextField(
                                value = passwordRepeatSignup,
                                onValueChange = { passwordRepeatSignup = it },
                                singleLine = true,
                                visualTransformation = PasswordVisualTransformation(),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                placeholder = { Text(stringResource(R.string.signup_input_repeat_p)) },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = MaterialTheme.colors.background
                                )
                            )
                        }

                        if (errorMessage) {
                            Text(
                                stringResource(R.string.signup_input_error),
                                style = MaterialTheme.typography.body1,
                                color = Color.Red
                            )
                        }
                    }
                    Column(
                        modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Bottom
                    ) {

                        Box {
                            Row(
                                modifier.align(Alignment.BottomStart)
                            ) {
                                Image(
                                    painter = painterResource(signupImg),
                                    contentDescription = "Plant",
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .width(413.dp),
                                )
                            }

                            Row(
                                modifier
                                    .fillMaxWidth()
                                    .align(Alignment.BottomEnd),
                                horizontalArrangement = Arrangement.End,
                            ) {
                                Button(
                                    onClick = {
                                        if (passwordSignup == passwordRepeatSignup) {
                                            onSignUp(userNameSignup, passwordSignup)
                                        } else {
                                            errorMessage = true
                                        }
                                    },
                                    enabled = userNameSignup.isNotBlank() && passwordSignup.isNotBlank(),
                                    modifier = Modifier
                                        .size(width = 206.dp, height = 65.dp)
                                        .clip(
                                            shape = RoundedCornerShape(
                                                topStart = 30.dp,
                                                bottomStart = 30.dp
                                            )
                                        ),
                                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),

                                    ) {
                                    Text(
                                        stringResource(R.string.login_button_signup),
                                        style = MaterialTheme.typography.body1
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    } //Popup end

    if (!showSignup) {

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
        Column {
            Column(
                modifier
                    .padding(startPadding)
            ) {
                Text(
                    text = stringResource(R.string.welcome),
                    style = headings1,
                )
                Text(
                    text = stringResource(R.string.back),
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
                        onClick = { showSignup = true },
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
                            .clip(
                                shape = RoundedCornerShape(
                                    topStart = 30.dp,
                                    bottomStart = 30.dp
                                )
                            ),
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

}

