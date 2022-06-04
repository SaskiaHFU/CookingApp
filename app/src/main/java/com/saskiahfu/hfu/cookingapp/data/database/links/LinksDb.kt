package com.saskiahfu.hfu.cookingapp.data.database.links

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "links")
data class LinksDb(
    @PrimaryKey
    val id: String,
    val name: String,
    val category: String,
    val uri: String,
)