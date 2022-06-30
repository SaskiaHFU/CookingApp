package com.saskiahfu.hfu.cookingapp.feature.recipes.ui.add

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import com.saskiahfu.hfu.cookingapp.feature.weekplan.ui.MealplanUI

@Composable
fun expandMenu(
    bfSelected: Boolean,
    luSelected: Boolean,
    diSelected: Boolean,
    meal: MealplanUI,
    name: String
) {
    var bfName by remember { mutableStateOf("") }
    var luName by remember { mutableStateOf("") }
    var diName by remember { mutableStateOf("") }
    var clicked by remember { mutableStateOf(false) }

    println(bfSelected)
    println(luSelected)
    println(diSelected)

    Column() {
        TextButton(
            onClick = {
                bfName = if (bfSelected) {
                    name
                } else {
                    meal.bfName
                }

                println(meal.bfName)
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor =
                if (clicked && bfSelected) MaterialTheme.colors.surface
                else MaterialTheme.colors.background
            ),
        ) {
            Text(
                text = "Breakfast",
                style = MaterialTheme.typography.body1
            )
        }
        TextButton(
            onClick = {
                luName = if (luSelected) {
                    name
                } else {
                    meal.luName
                }
                println(luName)
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor =
                if (clicked && bfSelected) MaterialTheme.colors.surface
                else MaterialTheme.colors.background
            ),
        ) {
            Text(
                text = "Lunch",
                style = MaterialTheme.typography.body1
            )
        }
        TextButton(
            onClick = {
                diName = if (diSelected) {
                    name
                } else {
                    meal.diName
                }
                println(meal.diName)
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor =
                if (clicked && bfSelected) MaterialTheme.colors.surface
                else MaterialTheme.colors.background
            ),
        ) {
            Text(
                text = "Dinner",
                style = MaterialTheme.typography.body1
            )
        }
    }

}