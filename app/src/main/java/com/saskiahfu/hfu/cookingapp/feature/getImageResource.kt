package com.saskiahfu.hfu.cookingapp.feature

import android.content.Context
import com.saskiahfu.hfu.cookingapp.domain.model.Recipe
import com.saskiahfu.hfu.cookingapp.domain.model.RecipeImg


fun getRecipeImgResource(recipe: Recipe, context: Context) =
    when (val img = recipe.img) {
        is RecipeImg.Local -> context.resources.getIdentifier(
            img.name,
            "drawable",
            context.packageName
        )
        is RecipeImg.Remote,
        RecipeImg.Unknown -> 0
    }
