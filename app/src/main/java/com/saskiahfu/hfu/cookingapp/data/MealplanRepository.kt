package com.saskiahfu.hfu.cookingapp.data

import com.saskiahfu.hfu.cookingapp.data.database.mealplan.DailyMealsDao
import com.saskiahfu.hfu.cookingapp.data.database.mealplan.mealFromDb
import com.saskiahfu.hfu.cookingapp.data.database.mealplan.mealToDb
import com.saskiahfu.hfu.cookingapp.domain.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MealplanRepository @Inject constructor(
    private val dao: DailyMealsDao,
) {
    suspend fun getAllMeals(): List<Mealplan> = dao.getAll().mapNotNull { mealFromDb(it) }

    fun observeAllMeals(): Flow<List<Mealplan>> =
        dao.observeAll().map { it.mapNotNull(::mealFromDb) }

    suspend fun getMealById(id: MealId): Mealplan? =
        dao.getById(id.value)?.let { mealFromDb(it) }

    suspend fun updateMeal(meal: Mealplan) {
        dao.update(
            mealToDb(meal)
        )
    }

    suspend fun addMeal(meal: Mealplan) {
        dao.insert(
            mealToDb(meal)
        )
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }
}