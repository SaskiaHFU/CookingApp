package com.saskiahfu.hfu.cookingapp.feature.cart.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.unit.dp
import com.saskiahfu.hfu.cookingapp.R
import com.saskiahfu.hfu.cookingapp.feature.main.modifier
import com.saskiahfu.hfu.cookingapp.feature.main.navigation.BottomNavigationItem

@Composable
fun CartItem(item: CartItemUI) {
    val textStyle = MaterialTheme.typography.body2
    var checked by remember { mutableStateOf(false) }
    var delete by remember { mutableStateOf(false) }

    if (delete) {
        println("delete")
    }

    Row(
        modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.Center,
    ) {
        Button(
            onClick = {
                if (!checked) {
                    checked = true
                } else if (checked) {
                    delete = true
                    //                TODO delete item
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background),
            border = BorderStroke(0.dp, MaterialTheme.colors.background),
        ) {
            Icon(
                painter = painterResource(
                    id = if (checked) {
                        R.drawable.ic_baseline_check_circle_24
                    } else {
                        R.drawable.ic_outline_radio_button_unchecked_24
                    }
                ),
                "Mark Item"
            )
            Spacer(modifier.width(10.dp))
            Text(
                text = item.item,
                style = textStyle
            )
        }
    }
}