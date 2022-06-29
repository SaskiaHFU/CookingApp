package com.saskiahfu.hfu.cookingapp.feature.weekplan.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.saskiahfu.hfu.cookingapp.domain.UpdateMealUseCase
import com.saskiahfu.hfu.cookingapp.domain.observeUseCase.ObserveMealplanUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealplanViewModel @Inject constructor(
    private val observeMeals: ObserveMealplanUseCase,
    private val updateMeals: UpdateMealUseCase
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

    fun onUpdateMeal(day: String) {
        viewModelScope.launch {

//            updateMeals().map {}
        }
    }

}