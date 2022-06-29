package com.saskiahfu.hfu.cookingapp.feature.weekplan.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.saskiahfu.hfu.cookingapp.R
import com.saskiahfu.hfu.cookingapp.domain.model.MealId
import com.saskiahfu.hfu.cookingapp.feature.main.days
import com.saskiahfu.hfu.cookingapp.feature.main.modifier

class MealplanUI(
//    val day: MealId,
    val day: String,
    var bfName: String,
    var luName: String,
    var diName: String,
)

@Composable
fun addMealButtonIcon(category: String) { //TODO code category/day Class or however it goes in
    IconButton(
        onClick = { /*TODO Code Add Meal Button*/ },
        modifier.size(25.dp)
    ) {
        Icon(
            Icons.Default.Add,
            contentDescription = "Add Meal",
            tint = MaterialTheme.colors.secondary,
        )
    }
}