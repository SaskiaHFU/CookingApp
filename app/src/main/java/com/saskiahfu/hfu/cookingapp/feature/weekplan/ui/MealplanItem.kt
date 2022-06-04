package com.saskiahfu.hfu.cookingapp.feature.weekplan.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.saskiahfu.hfu.cookingapp.feature.main.modifier

@Composable
fun MealplanItem(meal: MealplanUI) {
    val textRegular = MaterialTheme.typography.body1

    Row() {
        Text(
            text=meal.day.value,
            style = MaterialTheme.typography.h4
        )
    }
    //Breakfast Space
    Row() {
        Text(
            text = "Breakfast: ",
            style = textRegular,
        )
        if (meal.bfName == "") {
            addMealButtonIcon(category = "breakfast")
        } else {
            TextButton(
                onClick = { /*TODO*/ },
            ) {
                Text(
                    text = meal.bfName,
                    style = textRegular,
                )
            }
        }
    }

    //Lunch Space
    Row() {
        Text(
            text = "Lunch: ",
            style = textRegular, // TODO Make bold
        )
        if (meal.luName == "") {
            addMealButtonIcon(category = "lunch")
        } else {
            TextButton(
                onClick = { /*TODO*/ },
            ) {
                Text(
                    text = meal.luName,
                    style = textRegular,
                )
            }
        }
    }

    //Dinner Space
    Row() {
        Text(
            text = "Dinner: ",
            style = textRegular, // TODO Make bold
        )
        if (meal.diName == "") {
            addMealButtonIcon(category = "dinner")
        } else {
            TextButton(
                onClick = { /*TODO*/ },
                //modifier.width(50.dp)
            ) {
                Text(
                    text = meal.diName,
                    style = textRegular,
                )
            }
        }
    }

    Spacer(modifier.height(30.dp))

    Divider(
        color = MaterialTheme.colors.primary,
        modifier = Modifier
            .fillMaxWidth()
            .width(1.dp)
    )

    Spacer(modifier.height(30.dp))

}