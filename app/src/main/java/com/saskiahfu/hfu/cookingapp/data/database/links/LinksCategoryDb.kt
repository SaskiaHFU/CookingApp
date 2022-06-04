package com.saskiahfu.hfu.cookingapp.data.database.links

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "linkscategory")
data class LinkCategoryDb(
    @PrimaryKey
    val id: String,
    val name: String,
    val links: String,
)