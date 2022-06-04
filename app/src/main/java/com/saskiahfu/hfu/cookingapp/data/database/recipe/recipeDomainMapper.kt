package com.saskiahfu.hfu.cookingapp.data.database.recipe

import com.saskiahfu.hfu.cookingapp.data.database.recipe.RecipeDb
import com.saskiahfu.hfu.cookingapp.domain.model.*

fun recipeToDb(recipe: Recipe): RecipeDb = RecipeDb(
    id = recipe.id.value,
    name = recipe.name,
//    img = "recipe.img",
//    ingredients = recipe.ingredients,
//    steps = recipe.steps,
//    category = recipe.category,
    sourceName = recipe.sourceName,
    sourceUri = recipe.sourceUri
)

fun recipeFromDb(recipe: RecipeDb): Recipe? {
//    val img = when {
//        recipe.img != null -> RecipeImg.Local(recipe.img)
//        else -> RecipeImg.Unknown
//    }
    return Recipe.create(
        id = RecipeId(recipe.id),
        name = recipe.name,
//        img = img,
//        ingredients = recipe.ingredients,
//        steps = recipe.steps,
//        category = recipe.category,
        sourceName = recipe.sourceName,
        sourceUri = recipe.sourceUri
    )
}