package com.saskiahfu.hfu.cookingapp.data.database.recipe
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.Query
//import kotlinx.coroutines.flow.Flow
//
//@Dao
//interface RecipeCategoryDao {
//
//    @Insert
//    suspend fun insert(recipeCategory: RecipeCategoryDb)
//
//    @Query("SELECT * FROM recipeCategory")
//    suspend fun getAll(): List<RecipeCategoryDb>
//
//    @Query("SELECT * FROM recipeCategory")
//    fun observeAll(): Flow<List<RecipeCategoryDb>>
//
//    @Query("SELECT * FROM recipeCategory WHERE id = :id")
//    suspend fun getById(id: String): RecipeCategoryDb?
//
//}