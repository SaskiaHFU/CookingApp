package com.saskiahfu.hfu.cookingapp.feature.weekplan.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.saskiahfu.hfu.cookingapp.R
import com.saskiahfu.hfu.cookingapp.feature.main.modifier

@Composable
fun MealplanItem(meal: MealplanUI) {
    val textRegular = MaterialTheme.typography.body1

    Row() {
        Text(
//            text = meal.day.value,
            text = meal.day,
            style = MaterialTheme.typography.h4
        )
    }
    //Breakfast Space
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.mealplan_bf),
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
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.mealplan_lu),
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
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.mealplan_di),
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