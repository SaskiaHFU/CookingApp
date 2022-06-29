package com.saskiahfu.hfu.cookingapp.domain.observeUseCase

import com.saskiahfu.hfu.cookingapp.data.RecipeCategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ObserveRecipeCategoriesUseCase @Inject constructor(
    private val recipeCategoriesRepository: RecipeCategoryRepository,
) {
    operator fun invoke() =
        recipeCategoriesRepository.observeAllRecipeCategories().flowOn(Dispatchers.Default)
}