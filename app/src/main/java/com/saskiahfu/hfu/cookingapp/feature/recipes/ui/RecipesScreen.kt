package com.saskiahfu.hfu.cookingapp.feature.recipes.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.saskiahfu.hfu.cookingapp.feature.main.contentPadding
import com.saskiahfu.hfu.cookingapp.feature.main.menuIcon
import com.saskiahfu.hfu.cookingapp.feature.main.modifier
import com.saskiahfu.hfu.cookingapp.feature.main.pageDirection


@Composable
fun RecipesScreen(viewModel: RecipesViewModel = viewModel()) {
    val recipes by viewModel.bindUi(LocalContext.current).observeAsState(emptyList())
    RecipesScreenUI(recipes)
}

@Composable
private fun RecipesScreenUI(recipes: List<RecipeUI>) {
    Column(
        //modifier.verticalScroll(rememberScrollState())
    ) {
//Call Header
        menuIcon()

        Row(
            modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            pageDirection("My Recipes")
        }

//Categories
        printRecipeCategories()

//Content
        Box(
            modifier
                .padding(contentPadding)
        ) {

            Column {
                val scrollState = rememberLazyListState()
                LazyColumn(state = scrollState) {
                    items(recipes) { recipe ->
                        RecipeItem(recipe)
                    }
                }
            }
        }
    }
}
