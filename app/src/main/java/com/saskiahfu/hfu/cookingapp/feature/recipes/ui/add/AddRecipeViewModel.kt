package com.saskiahfu.hfu.cookingapp.feature.recipes.ui.add

import android.content.Context
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.saskiahfu.hfu.cookingapp.domain.AddRecipeUseCase
import com.saskiahfu.hfu.cookingapp.domain.UpdateMealUseCase
import com.saskiahfu.hfu.cookingapp.domain.observeUseCase.ObserveMealplanUseCase
import com.saskiahfu.hfu.cookingapp.domain.observeUseCase.ObserveRecipeCategoriesUseCase
import com.saskiahfu.hfu.cookingapp.domain.observeUseCase.ObserveRecipesUseCase
import com.saskiahfu.hfu.cookingapp.feature.recipes.ui.RecipeUI
import com.saskiahfu.hfu.cookingapp.feature.recipes.ui.categories.RecipeCategoryUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddRecipeViewModel @Inject constructor(
    private val observeRecipeCategory: ObserveRecipeCategoriesUseCase,
    private val addRecipe: AddRecipeUseCase,
    private val updateMealplan: UpdateMealUseCase
) : ViewModel() {
    fun bindUi(context: Context): LiveData<List<RecipeCategoryUI>> =
        observeRecipeCategory().map {
            it.map { recipe_category ->
                RecipeCategoryUI(
                    id = recipe_category.id,
                    name = recipe_category.name,
                )
            }
        }.asLiveData()



    fun onAddRecipe(
        name: String,
        img: String,
        ingredients: String,
        steps: String,
        category: String,
        sourceName: String,
        sourceUri: String
    ) {
        viewModelScope.launch {
            addRecipe(name, img, ingredients, steps, category, sourceName, sourceUri)
        }
    }

//erst get dann ändern dann gleiche ocject und update

    fun onUpdateMealplan(
        day: String,
        bfName: String,
        luName: String,
        diName: String
    ) {
        viewModelScope.launch {
            updateMealplan(day, bfName, luName, diName)
        }
    }

}


