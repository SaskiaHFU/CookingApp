package com.saskiahfu.hfu.cookingapp.feature.weekplan.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.saskiahfu.hfu.cookingapp.domain.observeUseCase.ObserveMealplanUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MealplanViewModel @Inject constructor(
    private val observeMeals: ObserveMealplanUseCase,
//private val updateMeal: UpdateMealUseCase,
) : ViewModel() {
fun bindUi(context: Context): LiveData<List<MealplanUI>> =
    observeMeals().map {
        it.map { meal ->
            MealplanUI(
                day = meal.day,
                bfName = meal.bfName,
                luName = meal.luName,
                diName = meal.diName,
            )
        }
    }.asLiveData()

//    fun onUpdateMeal(mealId: MealId) {
//        viewModelScope.launch {
//            updateMeal(mealId)
//        }
//    }
}