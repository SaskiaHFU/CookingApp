package com.saskiahfu.hfu.cookingapp.data.database.cart

import com.saskiahfu.hfu.cookingapp.data.database.buyableProductFromDb
import com.saskiahfu.hfu.cookingapp.domain.model.ShoppingCart
import com.saskiahfu.hfu.cookingapp.domain.model.ShoppingCartId

fun shoppingCartFromDb(id: ShoppingCartId, productsAndAmount: List<ProductAndAmount>): ShoppingCart {
    return if (productsAndAmount.isEmpty()) {
        ShoppingCart.Empty(id)
    } else {

        val buyableProducts = productsAndAmount.mapNotNull {
            buyableProductFromDb(it.product)
        }

        val products = buyableProducts.associateWith { buyableProduct ->
            productsAndAmount.firstOrNull { it.product.id == buyableProduct.product.id.value }?.cartProduct?.amount ?: 0
        }

        ShoppingCart.Unpayed.create(
            id = id,
            products = products,
            totalCost = products.entries.sumOf { it.key.price * it.value },
        )
    }
}
