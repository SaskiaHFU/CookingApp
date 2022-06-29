package com.saskiahfu.hfu.cookingapp.feature.recipes.ui.navigation


sealed class RecipeNavigationItem {
    abstract val routeName: String
    abstract val title: String
//    abstract val img: Int

    object RecipeSingle : RecipeNavigationItem() {
        override val routeName = "singlerecipe"
        override val title = ""

    }
}
