package com.saskiahfu.hfu.cookingapp.data.database.recipe

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeDb(
    @PrimaryKey
    val id: String,
    val name: String,
    val img: String?,
    val img_url: String?,
    val ingredients: String,
    val steps: String,
    val category: String,
    val sourceName: String,
    val sourceUri: String
)