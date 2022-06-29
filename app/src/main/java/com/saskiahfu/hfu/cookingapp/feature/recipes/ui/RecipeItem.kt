package com.saskiahfu.hfu.cookingapp.feature.recipes.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.saskiahfu.hfu.cookingapp.feature.main.modifier

//var singleRecipeID = ""

@Composable
fun RecipeItem(recipe: RecipeUI) {
    val textStyle = MaterialTheme.typography.body2

//   Button(
//        onClick = {
//            singleRecipeID = recipe.id.value
//        },
//        modifier
//            .height(165.dp)
//            .clip(RoundedCornerShape(30.dp))
//            .background(MaterialTheme.colors.secondary),
//        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
//   ) {
    Row(
        modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
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
//    }
//    Spacer(modifier.height(20.dp))
}
