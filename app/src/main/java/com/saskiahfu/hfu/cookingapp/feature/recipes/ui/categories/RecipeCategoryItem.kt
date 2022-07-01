package com.saskiahfu.hfu.cookingapp.feature.recipes.ui.categories

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.saskiahfu.hfu.cookingapp.R
import com.saskiahfu.hfu.cookingapp.feature.main.contentPadding
import com.saskiahfu.hfu.cookingapp.feature.main.modifier

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
