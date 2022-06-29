package com.saskiahfu.hfu.cookingapp.feature.recipes.ui.categories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.saskiahfu.hfu.cookingapp.domain.observeUseCase.ObserveRecipeCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class RecipeCategoriesViewModel @Inject constructor(
    private val observeRecipeCategory: ObserveRecipeCategoriesUseCase,
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
}