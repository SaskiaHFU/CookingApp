package com.saskiahfu.hfu.cookingapp.domain

import android.util.Log
import com.saskiahfu.hfu.cookingapp.data.MealplanRepository
import com.saskiahfu.hfu.cookingapp.data.network.AddMealToPlanRequestDto
import com.saskiahfu.hfu.cookingapp.data.network.WebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateMealUseCase @Inject constructor(
    private val webService: WebService,
    private val mealplanRepository: MealplanRepository,
) {

    suspend operator fun invoke(
        day: String,
        bfName: String,
        luName: String,
        diName: String
    ) = withContext(Dispatchers.Default) {

        kotlin.runCatching {
            val slot = mealplanRepository.getMealByDay(day)
            val newSlot = slot?.copy(
                bfName = bfName,
                luName = luName,
                diName = diName
            )

//    new slot übergeben an update
//           dataclass -> copy
//           class -> kein copy (aufwändiger)

            if (newSlot != null) {
                mealplanRepository.updateMeal(newSlot)
                webService.updateMeal(
                    day = day,
                    AddMealToPlanRequestDto(
                        day,
                        bfName,
                        luName,
                        diName
                    )
                )
            }
        }
    }.fold(
        { Log.e("MEALPLAN", "ok") },
        { Log.e("MEALPLAN", it.toString(), it) },
    )
}