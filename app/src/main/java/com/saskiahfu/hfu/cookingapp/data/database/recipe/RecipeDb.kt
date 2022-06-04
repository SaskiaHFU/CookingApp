package com.saskiahfu.hfu.cookingapp.data.database.recipe

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeDb(
    @PrimaryKey
    val id: String,
    val name: String,
//    val img: String?,
//    val ingredients: List<String>,
//    val steps: List<String>,
//    val category: String,
    val sourceName: String,
    val sourceUri: String
)