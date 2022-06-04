package com.saskiahfu.hfu.cookingapp.data.database.links

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LinksCategoryDao {

    @Insert
    suspend fun insert(cat: LinkCategoryDb)

    @Query("SELECT * FROM linkscategory")
    suspend fun getAll(): List<LinkCategoryDb>

    @Query("SELECT * FROM linkscategory")
    fun observeAll(): Flow<List<LinkCategoryDb>>

    @Query("SELECT * FROM linkscategory WHERE id = :id")
    suspend fun getById(id: String): LinkCategoryDb?

    @Query("DELETE FROM linkscategory")
    suspend fun deleteAll()
}