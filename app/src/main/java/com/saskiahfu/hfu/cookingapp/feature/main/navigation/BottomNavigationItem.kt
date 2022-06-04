package com.saskiahfu.hfu.cookingapp.feature.main.navigation

import com.saskiahfu.hfu.cookingapp.R


sealed class BottomNavigationItem {
    abstract val routeName: String
    abstract val title: Int
    abstract val icon: Int

    object Recipes : BottomNavigationItem() {
        override val title = R.string.recipes_title_navigation
        override val icon = R.drawable.ic_restaurant_white_24dp
        override val routeName = "recipes"
    }
//
//    object AddRecipe : BottomNavigationItem() {
//        override val title = R.string.recipe_button_add
//        override val icon = R.drawable.ic_baseline_add_24
//        override val routeName = "addrecipe"
//    }

    object Cart : BottomNavigationItem() {
        override val title = R.string.cart_title_navigation
        override val icon = R.drawable.ic_outline_shopping_cart_24
        override val routeName = "cart"
    }

    object Profile : BottomNavigationItem() {
        override val title = R.string.profile_title_navigation
        override val icon = R.drawable.ic_baseline_person_24
        override val routeName = "profile"
    }

    object Links : BottomNavigationItem() {
        override val title = R.string.linklist_title_navigation
        override val icon = R.drawable.ic_outline_link_24
        override val routeName = "linklist"
    }

    object Home : BottomNavigationItem() {
        override val title = R.string.home_title_navigation
        override val icon = R.drawable.ic_outline_home_24
        override val routeName = "home"
    }

    object Week : BottomNavigationItem() {
        override val title = R.string.week_title_navigation
        override val icon = R.drawable.ic_baseline_dashboard_24
        override val routeName = "weekplan"
    }
}
