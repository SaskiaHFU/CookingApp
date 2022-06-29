package com.saskiahfu.hfu.cookingapp.feature

import android.content.Context
import com.saskiahfu.hfu.cookingapp.domain.model.BuyableProduct
import com.saskiahfu.hfu.cookingapp.domain.model.ProductIcon
import com.saskiahfu.hfu.cookingapp.domain.model.Recipe
import com.saskiahfu.hfu.cookingapp.domain.model.RecipeImg


fun getProductIconResource(buyableProduct: BuyableProduct, context: Context) =
    when (val icon = buyableProduct.product.icon) {
        is ProductIcon.Local -> context.resources.getIdentifier(
            icon.name,
            "drawable",
            context.packageName
        )
        is ProductIcon.Remote,
        ProductIcon.Unknown -> 0
    }

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
