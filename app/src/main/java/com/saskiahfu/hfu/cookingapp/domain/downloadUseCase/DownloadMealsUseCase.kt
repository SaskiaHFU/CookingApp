package com.saskiahfu.hfu.cookingapp.domain.downloadUseCase

import android.util.Log
import com.saskiahfu.hfu.cookingapp.data.MealplanRepository
import com.saskiahfu.hfu.cookingapp.data.network.WebService
import com.saskiahfu.hfu.cookingapp.domain.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DownloadMealsUseCase @Inject constructor(
    private val webService: WebService,
    private val mealplanRepository: MealplanRepository,
) {
    suspend operator fun invoke() = withContext(Dispatchers.Default) {
        kotlin.runCatching {
            webService.getMeals().mapNotNull {
                Mealplan.create(
                    MealId(it.day),
                    it.bfName,
                    it.luName,
                    it.diName,
                )

            }.forEach{
                mealplanRepository.addMeal(it)
            }
        }
    }.fold(
        { Log.e("COOKING", "ok")},
        {Log.e("COOKING", it.toString(), it)},
    )
}