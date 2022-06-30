package com.saskiahfu.hfu.cookingapp.feature.recipes.ui.categories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.saskiahfu.hfu.cookingapp.domain.AddCategoryUseCase
import com.saskiahfu.hfu.cookingapp.domain.observeUseCase.ObserveRecipeCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeCategoriesViewModel @Inject constructor(
    private val observeRecipeCategory: ObserveRecipeCategoriesUseCase,
    private val addCategory: AddCategoryUseCase
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

    fun onAddCategory(
        name: String
    ) {
        viewModelScope.launch {
            addCategory(name)
        }
    }
}