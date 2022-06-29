package com.saskiahfu.hfu.cookingapp.feature.recipes.ui.add

import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import com.saskiahfu.hfu.cookingapp.feature.weekplan.ui.MealplanUI

@Composable
fun addTimeButton(time: String, recipe: String, onClick: (String) -> Unit) {
    var clicked = false

    var dbVar: MealplanUI

//    TextButton(
//        onClick = {
////            clicked = true
//            if (time == "Breakfast")
//
//        },
//        colors = ButtonDefaults.buttonColors(
//            backgroundColor =
////            if (clicked) MaterialTheme.colors.surface
////            else
//                MaterialTheme.colors.background
//        ),
//    ) {
//
//        Text(
//            text = time,
//            style = MaterialTheme.typography.body1
//        )
//    }
}