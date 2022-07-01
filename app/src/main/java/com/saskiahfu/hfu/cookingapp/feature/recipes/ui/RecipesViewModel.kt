package com.saskiahfu.hfu.cookingapp.feature.recipes.ui

import android.content.Context
import androidx.lifecycle.*
import com.saskiahfu.hfu.cookingapp.data.RecipeRepository
import com.saskiahfu.hfu.cookingapp.data.network.WebService
import com.saskiahfu.hfu.cookingapp.domain.model.RecipeImg
import com.saskiahfu.hfu.cookingapp.domain.observeUseCase.ObserveRecipesUseCase
import com.saskiahfu.hfu.cookingapp.feature.getRecipeImgResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val observeRecipes: ObserveRecipesUseCase,
    private val recipeRepository: RecipeRepository,
) : ViewModel() {
    private val mutableRecipes = MutableLiveData<List<RecipeUI>>(emptyList())

    fun bindUi(context: Context): LiveData<List<RecipeUI>> {
        viewModelScope.launch {
            observeRecipes().map {
                val recipes = it.map { recipe ->
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
                mutableRecipes.value = recipes
            }
        }
        return mutableRecipes
    }

    fun onCategorySelect(categoryName: String, context: Context) {
        viewModelScope.launch {
            val recipes = recipeRepository.getAllRecipes().filter {
                it.category == categoryName
            }.map { recipe ->
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
            mutableRecipes.value = recipes
        }
    }
}