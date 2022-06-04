package com.saskiahfu.hfu.cookingapp.domain.observeUseCase

import com.saskiahfu.hfu.cookingapp.data.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ObserveRecipesUseCase @Inject constructor(
    private val recipesRepository: RecipeRepository,
){
    operator  fun invoke() = recipesRepository.observeAllRecipes().flowOn(Dispatchers.Default)
}