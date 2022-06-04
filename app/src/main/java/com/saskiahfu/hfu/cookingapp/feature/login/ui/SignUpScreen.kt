package com.saskiahfu.hfu.cookingapp.feature.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.saskiahfu.hfu.cookingapp.R
import com.saskiahfu.hfu.cookingapp.feature.main.containerPadding
import com.saskiahfu.hfu.cookingapp.feature.main.modifier
import com.saskiahfu.hfu.cookingapp.feature.main.signInPadding

@Composable
fun SignUpScreen(viewModel: SignUpViewModel = viewModel()) {
    SignUpScreenUi(viewModel::onSignUp)
}

@Composable
fun SignUpScreenUi(
    onSignUp: (username: String, password: String) -> Unit,
) {
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
                text = "Sign Up",
                style = MaterialTheme.typography.h1,
            )

            IconButton(
                onClick = {
                    //text.value = "Refresh clicked. "
                },
            ) {
                Icon(
                    Icons.Filled.Close, //TODO Change Icon
                    contentDescription = "Close Sign Up",
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
                    Text(
                        text = "Email",
                        style = font,
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Text(
                        text = "Name",
                        style = font,
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Text(
                        text = "Password",
                        style = font,
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Text(
                        text = "Repeat Password",
                        style = font,
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

@Preview
@Composable
fun SignUpScreen_Preview() {
    SignUpScreenUi { _, _ -> }
}