package com.saskiahfu.hfu.cookingapp.data.database.recipe

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Insert
    suspend fun insert(recipes: RecipeDb)

    @Query("SELECT * FROM recipes")
    suspend fun getAll(): List<RecipeDb>

    @Query("SELECT * FROM recipes")
    fun observeAll(): Flow<List<RecipeDb>>

    @Query("SELECT * FROM recipes WHERE id = :id")
    suspend fun getById(id: String): RecipeDb?

    @Query("DELETE FROM recipes")
    suspend fun deleteAll()
}
