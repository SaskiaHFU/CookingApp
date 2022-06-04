package com.saskiahfu.hfu.cookingapp.feature.main.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.saskiahfu.hfu.cookingapp.feature.cart.ui.ShoppingCartScreen
import com.saskiahfu.hfu.cookingapp.feature.cart.ui.ShoppingCartViewModel
import com.raysono.hfu.fridgepay.feature.profile.ui.ProfileScreen
import com.raysono.hfu.fridgepay.feature.profile.ui.ProfileViewModel
import com.saskiahfu.hfu.cookingapp.feature.home.ui.HomeScreen
import com.saskiahfu.hfu.cookingapp.feature.home.ui.HomeViewModel
import com.saskiahfu.hfu.cookingapp.feature.links.ui.LinksScreen
import com.saskiahfu.hfu.cookingapp.feature.links.ui.LinksViewModel
//import com.saskiahfu.hfu.cookingapp.feature.recipes.ui.AddRecipeScreen
import com.saskiahfu.hfu.cookingapp.feature.recipes.ui.RecipesScreen
import com.saskiahfu.hfu.cookingapp.feature.recipes.ui.RecipesViewModel
import com.saskiahfu.hfu.cookingapp.feature.weekplan.ui.MealplanScreen
import com.saskiahfu.hfu.cookingapp.feature.weekplan.ui.MealplanViewModel

@Composable
fun MainNavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavigationItem.Home.routeName) {

        composable(BottomNavigationItem.Recipes.routeName) {
            val vm = hiltViewModel<RecipesViewModel>()
            RecipesScreen(vm)
        }

//        composable(BottomNavigationItem.AddRecipe.routeName) {
//            AddRecipeScreen()
//        }

        composable(BottomNavigationItem.Cart.routeName) {
            val vm = hiltViewModel<ShoppingCartViewModel>()
            ShoppingCartScreen(vm)
        }
        composable(BottomNavigationItem.Profile.routeName) {
            val vm = hiltViewModel<ProfileViewModel>()
            ProfileScreen(vm)
        }

        composable(BottomNavigationItem.Home.routeName) {
            val vm = hiltViewModel<HomeViewModel>()
            HomeScreen(vm)
        }
        composable(BottomNavigationItem.Links.routeName) {
            val vm = hiltViewModel<LinksViewModel>()
            LinksScreen(vm)
        }
        composable(BottomNavigationItem.Week.routeName) {
            val vm = hiltViewModel<MealplanViewModel>()
            MealplanScreen(vm)
        }

    }
}

