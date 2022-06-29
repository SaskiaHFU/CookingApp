package com.saskiahfu.hfu.cookingapp.feature.recipes.ui.categories

import android.content.Context
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun RecipeCategoryItem(recipeCategory: RecipeCategoryUI, onClick: (String, Context) -> Unit) {
    val context = LocalContext.current
    TextButton(
        onClick = {
            onClick(recipeCategory.name, context)

        },
    ) {
        Text(
            text = recipeCategory.name,
            style = MaterialTheme.typography.body1,
        )
    }
}

@Composable
fun AddRecipeCategoryItem(recipeCategory: RecipeCategoryUI) {
    Text(
        text = recipeCategory.name,
        style = MaterialTheme.typography.body1,
    )
}
