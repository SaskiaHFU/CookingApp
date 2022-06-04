package com.saskiahfu.hfu.cookingapp.feature.recipes.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.saskiahfu.hfu.cookingapp.domain.model.RecipeId
import com.saskiahfu.hfu.cookingapp.feature.main.categoryPadding
import com.saskiahfu.hfu.cookingapp.feature.main.modifier

class RecipeUI(
    val id: RecipeId,
    val name: String,
//    @DrawableRes val img: Int?,
//    val ingredients: List<String>,
//    val steps: List<String>,
//    val category: String,
    val sourceName: String,
    val sourceUri: String,
)

val allRecCat = listOf(
    "Breakfast", "Lunch", "Dinner", "Kids", "Drinks"
)

@Composable
fun printRecipeCategories() {
    Row(
        modifier
            .horizontalScroll(rememberScrollState())
            .padding(categoryPadding),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        for (cats in allRecCat) {

            TextButton(
                onClick = {
                    //TODO Code Choose Month Button action
                    //Give attribute active as class parameter
                },
                ) {
                Text(
                    text = cats,
                    style = MaterialTheme.typography.body1,
                    )
            }
        }
    }
}
