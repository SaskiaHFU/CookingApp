package com.saskiahfu.hfu.cookingapp.feature.recipes.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.saskiahfu.hfu.cookingapp.feature.main.modifier

@Composable
fun RecipeItem(recipe: RecipeUI) {
    val textStyle = MaterialTheme.typography.body2

    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier
                .size(width = 115.dp, height = 165.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(30.dp))
                .background(MaterialTheme.colors.secondary),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
//            Image(
//                painter = painterResource(R.dra),
//                contentDescription = "Recipe Image",
//                contentScale = ContentScale.Fit
//            )

                Text(
                    text = recipe.name,
                    style = textStyle
                )
            }
        }
    }
}