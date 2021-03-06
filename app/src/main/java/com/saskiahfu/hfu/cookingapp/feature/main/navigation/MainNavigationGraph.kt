package com.saskiahfu.hfu.cookingapp.feature.main.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.saskiahfu.hfu.cookingapp.feature.cart.ui.CartScreen
import com.saskiahfu.hfu.cookingapp.feature.cart.ui.CartViewModel
import com.saskiahfu.hfu.cookingapp.feature.profile.ui.ProfileScreen
import com.saskiahfu.hfu.cookingapp.feature.profile.ui.ProfileViewModel
import com.saskiahfu.hfu.cookingapp.feature.login.ui.LoginScreen
import com.saskiahfu.hfu.cookingapp.feature.login.ui.LoginViewModel
import com.saskiahfu.hfu.cookingapp.feature.recipes.ui.categories.RecipeCategoriesViewModel
import com.saskiahfu.hfu.cookingapp.feature.recipes.ui.RecipesScreen
import com.saskiahfu.hfu.cookingapp.feature.recipes.ui.RecipesViewModel
import com.saskiahfu.hfu.cookingapp.feature.recipes.ui.add.AddRecipeScreen
import com.saskiahfu.hfu.cookingapp.feature.recipes.ui.add.AddRecipeViewModel
import com.saskiahfu.hfu.cookingapp.feature.recipes.ui.single.SingleRecipeScreen
import com.saskiahfu.hfu.cookingapp.feature.recipes.ui.single.SingleRecipeViewModel
import com.saskiahfu.hfu.cookingapp.feature.weekplan.ui.MealplanScreen
import com.saskiahfu.hfu.cookingapp.feature.weekplan.ui.MealplanViewModel


@Composable
fun MainNavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavigationItem.Week.routeName) {
        composable(BottomNavigationItem.Login.routeName) {
            val vm = hiltViewModel<LoginViewModel>()
            LoginScreen(vm)
        }
        composable(BottomNavigationItem.RecipeSingle.routeName) {
            val vm = hiltViewModel<SingleRecipeViewModel>()
            val vmM = hiltViewModel<MealplanViewModel>()
            SingleRecipeScreen(vm, vmM, navController)
        }
        composable(BottomNavigationItem.Profile.routeName) {
            val vm = hiltViewModel<ProfileViewModel>()
            ProfileScreen(vm)
        }
        composable(BottomNavigationItem.AddRecipe.routeName) {
            val vm = hiltViewModel<AddRecipeViewModel>()
            val vmM = hiltViewModel<MealplanViewModel>()
            AddRecipeScreen(vm, vmM, navController)
        }
//        Bottom
        composable(BottomNavigationItem.Recipes.routeName) {
            val vmR = hiltViewModel<RecipesViewModel>()
            val vmRC = hiltViewModel<RecipeCategoriesViewModel>()
            RecipesScreen(vmR, vmRC, navController)
        }
        composable(BottomNavigationItem.Cart.routeName) {
            val vm = hiltViewModel<CartViewModel>()
            CartScreen(vm)
        }
//        composable(BottomNavigationItem.Home.routeName) {
//            val vm = hiltViewModel<RecipeCategoriesViewModel>()
//            HomeScreen(vm, navController)
//        }
        composable(BottomNavigationItem.Week.routeName) {
            val vm = hiltViewModel<MealplanViewModel>()
            MealplanScreen(vm)
        }
    }
}


