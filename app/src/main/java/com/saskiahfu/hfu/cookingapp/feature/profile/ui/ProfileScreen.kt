package com.saskiahfu.hfu.cookingapp.feature.profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.saskiahfu.hfu.cookingapp.R
import com.saskiahfu.hfu.cookingapp.feature.main.modifier


@Composable
fun ProfileScreen(viewModel: ProfileViewModel = viewModel()) {
    ProfileScreenUi(viewModel::onLogout)
}

@Composable
private fun ProfileScreenUi(onLogout: () -> Unit) {

    val regularText = MaterialTheme.typography.body1

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth(),
    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//        ) {
//            Button(
//                modifier = Modifier.padding(16.dp),
//                onClick = { onLogout() },
//            ) {
//                Text(
//                    stringResource(R.string.profile_button_logout),
//                    fontWeight = FontWeight.Bold,
//                )
//            }
//        }


//        Logout Button
        Column(
            modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom,
        ) {


            Box() {

                Row(
                    modifier.align(Alignment.BottomStart)
                ) {
                    Image(
                        painter = painterResource(R.drawable.plant_signup),
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
                            onLogout()
                        },
                        modifier = Modifier
                            .size(width = 206.dp, height = 65.dp)
                            .clip(shape = RoundedCornerShape(topStart = 30.dp, bottomStart = 30.dp)),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),

                        ) {
                        Text(
                            text = stringResource(R.string.profile_button_logout),
                            style = regularText
                        )
                    }
                }
            }


//            Row(
//                modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.End
//            ) {
//                Button(
//                    onClick = {
//                        onLogout()
//                    },
//                    modifier = Modifier
//                        .size(width = 206.dp, height = 65.dp)
//                        .clip(shape = RoundedCornerShape(topStart = 30.dp, bottomStart = 30.dp)),
//                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
//
//                    ) {
//                    Text(
//                        text = stringResource(R.string.profile_button_logout),
//                        style = regularText
//                    )
//                }
//            }

        }
    }
}

@Preview
@Composable
fun ProfileScreen_Preview() {
    ProfileScreenUi() {}
}
