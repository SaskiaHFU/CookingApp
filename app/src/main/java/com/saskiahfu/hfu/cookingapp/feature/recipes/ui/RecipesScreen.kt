package com.saskiahfu.hfu.cookingapp.feature.recipes.ui

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.saskiahfu.hfu.cookingapp.feature.main.*
import com.saskiahfu.hfu.cookingapp.feature.main.navigation.BottomNavigationItem
import com.saskiahfu.hfu.cookingapp.feature.recipes.ui.categories.RecipeCategoriesViewModel
import com.saskiahfu.hfu.cookingapp.feature.recipes.ui.categories.RecipeCategoryItem
import com.saskiahfu.hfu.cookingapp.feature.recipes.ui.categories.RecipeCategoryUI

var singleRecipeID = ""

@Composable
fun RecipesScreen(
    viewModel: RecipesViewModel = viewModel(),
    viewModelCat: RecipeCategoriesViewModel = viewModel(),
//    viewModelSingle: SingleRecipesViewModel = viewModel(),
    navController: NavController
) {
    val recipe by viewModel.bindUi(LocalContext.current).observeAsState(emptyList())
    val recipeCategories by viewModelCat.bindUi(LocalContext.current).observeAsState(emptyList())

    RecipeScreenUI(
        recipe,
        recipeCategories,
        viewModel::onCategorySelect,
//        viewModelSingle::onSelectedRecipe,
        navController
    )
}

@Composable
private fun RecipeScreenUI(
    recipes: List<RecipeUI>,
    recipe_categories: List<RecipeCategoryUI>,
    onClick: (String, Context) -> Unit,
//    onRecipeSelect: (String) -> Unit,
    navController: NavController
) {

    var selected by remember { mutableStateOf("") }
    val scrollState = rememberLazyListState()
    val context = LocalContext.current

    Column() {
//Call Header
//        menuIcon()
//        Row(
//            modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            pageDirection("My Recipes")
//        }

//Categories
        LazyRow(
            modifier.padding(categoryPadding),
            state = scrollState,
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(recipe_categories) { cats ->
                RecipeCategoryItem(cats, onClick)
            }
        }

//Content
        Box(
            modifier
                .padding(contentPadding)
        ) {
            LazyColumn(state = scrollState) {
                items(recipes) { recipe ->
                    Button(
                        onClick = {
//                            val name = recipe.name.lowercase().replace(" ", "")
                            navController.navigate(BottomNavigationItem.RecipeSingle.routeName)
                            singleRecipeID = recipe.id.value

//                            val currentRoute =
//                                navController.currentBackStackEntry?.destination?.route
//                            println(currentRoute)
//                            println(recipe.name.lowercase().replace(" ", ""))

//                            selected = recipe.id.value
//                            onRecipeSelect(selected)
                        },
                        modifier
                            .height(165.dp)
                            .clip(RoundedCornerShape(30.dp))
                            .background(MaterialTheme.colors.secondary),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
                    ) {
                        RecipeItem(recipe)
                    }
                    Spacer(modifier.height(20.dp))
                }
            }
        }
    }
}
