package com.saskiahfu.hfu.cookingapp.data.database.recipe
//
//import com.saskiahfu.hfu.cookingapp.domain.model.*
//
//fun recipeCatToDb(recipeCat: RecipeCategory): RecipeCategoryDb = RecipeCategoryDb(
//    id = recipeCat.id.value,
//    name = recipeCat.name,
//    recipes = recipeCat.recipes,
//    )
//
//fun recipeCatFromDb(recipeCat: RecipeCategoryDb): RecipeCategory? {
//    return RecipeCategory.create(
//        id = RecipeCategoryId(recipeCat.id),
//        name = recipeCat.name,
//        recipes = recipeCat.recipes,
//    )
//}