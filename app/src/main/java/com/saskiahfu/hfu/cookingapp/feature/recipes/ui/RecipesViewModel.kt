package com.saskiahfu.hfu.cookingapp.feature.recipes.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.saskiahfu.hfu.cookingapp.domain.observeUseCase.ObserveRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val observeRecipes: ObserveRecipesUseCase,
) : ViewModel() {
    fun bindUi(context: Context): LiveData<List<RecipeUI>> =
        observeRecipes().map {
            it.map { recipe ->
                RecipeUI(
                    id = recipe.id,
                    name = recipe.name,
//                    img = getRecipeImgResource(recipe, context),
//                    ingredients = recipe.ingredients,
//                    steps = recipe.steps,
//                    category = recipe.category,
                    sourceName = recipe.sourceName,
                    sourceUri = recipe.sourceUri
                )
            }
        }.asLiveData()
}