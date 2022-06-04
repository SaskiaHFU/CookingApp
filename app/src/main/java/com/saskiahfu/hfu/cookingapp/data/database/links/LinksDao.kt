package com.saskiahfu.hfu.cookingapp.data.database.links

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LinksDao {

    @Insert
    suspend fun insert(links: LinksDb)

    @Query("SELECT * FROM links")
    suspend fun getAll(): List<LinksDb>

    @Query("SELECT * FROM links")
    fun observeAll(): Flow<List<LinksDb>>

    @Query("SELECT * FROM links WHERE id = :id")
    suspend fun getById(id: String): LinksDb?

    @Query("DELETE FROM links")
    suspend fun deleteAll()
}