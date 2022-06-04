package com.saskiahfu.hfu.cookingapp.data.database.mealplan

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mealplan")
data class MealplanDb(
    @PrimaryKey
    val day: String,
    val bfName: String,
    val luName: String,
    val diName: String,
)