package com.saskiahfu.hfu.cookingapp.feature.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.saskiahfu.hfu.cookingapp.feature.main.navigation.BottomNavigationItem
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.saskiahfu.hfu.cookingapp.R
import com.saskiahfu.hfu.cookingapp.feature.main.containerPadding
import com.saskiahfu.hfu.cookingapp.feature.main.modifier
import com.saskiahfu.hfu.cookingapp.feature.main.signInPadding

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = viewModel(),

    ) {
    SignUpScreenUi(viewModel::onSignUp)
}

@Composable
fun SignUpScreenUi(
    onSignUp: (username: String, password: String) -> Unit,

    ) {

    val navController = rememberNavController()


    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordRepeat by remember { mutableStateOf("") }
    var passwordMatch by remember { mutableStateOf(false) }

//Upper Part
    Column(
        modifier
            .fillMaxWidth(),
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
                    //text.value = "Refresh clicked. "
                    navController.navigate(BottomNavigationItem.Login.routeName) //TODO geht nicht
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
        val font = MaterialTheme.typography.body1
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
                        value = userName,
                        onValueChange = { userName = it },
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
                        value = password,
                        onValueChange = { password = it },
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
                        value = passwordRepeat,
                        onValueChange = { passwordRepeat = it },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        placeholder = { Text(stringResource(R.string.signup_input_repeat_p)) },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = MaterialTheme.colors.background
                        )
                    )
                }
            }

            Column(
                modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom
            ) {

                Box(
                ) {
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
                                onSignUp(userName, password)
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

//@Preview
//@Composable
//fun SignUpScreen_Preview() {
//    SignUpScreenUi { _, _ -> }
//}