package com.saskiahfu.hfu.cookingapp.domain.observeUseCase

import com.saskiahfu.hfu.cookingapp.data.MealplanRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ObserveMealplanUseCase @Inject constructor(
    private val mealplanRepository: MealplanRepository,
){
    operator  fun invoke() = mealplanRepository.observeAllMeals().flowOn(Dispatchers.Default)
}