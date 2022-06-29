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

    suspend fun getMealById(id: Mealplan): Mealplan? =
        dao.getById(id.day)?.let { mealFromDb(it) }

    suspend fun getMealByDay(id: String): Mealplan? =
        dao.getById(id)?.let { mealFromDb(it) }

    suspend fun updateMeal(newMeal: Mealplan) {
        dao.update(
            mealToDb(newMeal)
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