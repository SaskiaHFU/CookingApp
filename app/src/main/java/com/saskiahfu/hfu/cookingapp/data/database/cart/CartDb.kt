package com.saskiahfu.hfu.cookingapp.data.database.cart

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class CartDb(
    @PrimaryKey
    val id: String,
    val item: String,
)