package com.saskiahfu.hfu.cookingapp.data.database.cart

import com.saskiahfu.hfu.cookingapp.domain.model.*

fun cartItemToDb(item: CartItem): CartDb = CartDb(
    id = item.id.value,
    item = item.item
)

fun cartItemFromDb(item: CartDb): CartItem? {
    return CartItem.create(
        id = CartItemId(item.id),
        item = item.item
    )
}


//fun shoppingCartFromDb(id: ShoppingCartId, productsAndAmount: List<ProductAndAmount>): ShoppingCart {
//    return if (productsAndAmount.isEmpty()) {
//        ShoppingCart.Empty(id)
//    } else {
//
//        val buyableProducts = productsAndAmount.mapNotNull {
//            buyableProductFromDb(it.product)
//        }
//
//        val products = buyableProducts.associateWith { buyableProduct ->
//            productsAndAmount.firstOrNull { it.product.id == buyableProduct.product.id.value }?.cartProduct?.amount ?: 0
//        }
//
//        ShoppingCart.Unpayed.create(
//            id = id,
//            products = products,
//            totalCost = products.entries.sumOf { it.key.price * it.value },
//        )
//    }
//}

