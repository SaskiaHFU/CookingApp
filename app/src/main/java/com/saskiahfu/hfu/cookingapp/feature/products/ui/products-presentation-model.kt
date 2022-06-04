package com.saskiahfu.hfu.cookingapp.feature.products.ui

import androidx.annotation.DrawableRes
import com.saskiahfu.hfu.cookingapp.domain.model.ProductId

class ProductUI(
    val id: ProductId,
    val name: String,
    val description: String,
    @DrawableRes val icon: Int?,
    val iconUrl: String?,
)
