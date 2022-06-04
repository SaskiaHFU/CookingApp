package com.saskiahfu.hfu.cookingapp.data.database.mealplan

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyMealsDao {

    @Insert
    suspend fun insert(meal: MealplanDb)

    @Update
    suspend fun update(meal: MealplanDb)

    @Query("SELECT * FROM mealplan")
    suspend fun getAll(): List<MealplanDb>

    @Query("SELECT * FROM mealplan")
    fun observeAll(): Flow<List<MealplanDb>>

    @Query("SELECT * FROM mealplan WHERE day = :day")
    suspend fun getById(day: String): MealplanDb?

    @Query("DELETE FROM mealplan")
    suspend fun deleteAll()
}