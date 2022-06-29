package com.saskiahfu.hfu.cookingapp.feature.recipes.ui.single

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.*
import com.saskiahfu.hfu.cookingapp.data.network.WebService
import com.saskiahfu.hfu.cookingapp.domain.model.RecipeImg
import com.saskiahfu.hfu.cookingapp.domain.observeUseCase.ObserveRecipesUseCase
import com.saskiahfu.hfu.cookingapp.feature.getRecipeImgResource
import com.saskiahfu.hfu.cookingapp.feature.recipes.ui.RecipeUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SingleRecipeViewModel @Inject constructor(
    private val observeRecipes: ObserveRecipesUseCase,
) : ViewModel() {
    fun bindUi(context: Context): LiveData<List<RecipeUI>> =
        observeRecipes().map {
            it.map { recipe ->
                RecipeUI(
                    id = recipe.id,
                    name = recipe.name,
                        img = (recipe.img as? RecipeImg.Local)?.let {
                            getRecipeImgResource(
                                recipe, context
                            )
                        },
                        img_url = (recipe.img as? RecipeImg.Remote)?.let { "${WebService.BASE_URL}${it.url}" },
                    ingredients = recipe.ingredients,
                    steps = recipe.steps,
                    category = recipe.category,
                    sourceName = recipe.sourceName,
                    sourceUri = recipe.sourceUri
                )
            }
        }.asLiveData()
}

//    fun onSelectedRecipe(id: String) {
//        println("select recipe fun")
////        singleRecipeID = id
//
//        viewModelScope.launch {
//            val recipe = recipeRepository.getAllRecipes().filter {
//                it.id.value == id
//            }.map { recipe ->
//                RecipeUI(
//                    id = recipe.id,
//                    name = recipe.name,
////                    img = getRecipeImgResource(recipe, context),
//                    ingredients = recipe.ingredients,
//                    steps = recipe.steps,
//                    category = recipe.category,
//                    sourceName = recipe.sourceName,
//                    sourceUri = recipe.sourceUri
//                )
//            }
//            mutableRecipe.value = recipe
//        }
//    }



