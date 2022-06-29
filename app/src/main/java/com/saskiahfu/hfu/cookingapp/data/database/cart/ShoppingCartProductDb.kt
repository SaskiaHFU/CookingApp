package com.saskiahfu.hfu.cookingapp.data.database.cart

//import androidx.room.Embedded
//import androidx.room.Entity
//import androidx.room.ForeignKey
//import androidx.room.Relation
//import com.saskiahfu.hfu.cookingapp.data.database.ProductDb
//
////@Entity(
////    tableName = "shopping_cart_product",
////    primaryKeys = ["productId"],
////    foreignKeys = [
////        ForeignKey(
////            entity = ProductDb::class,
////            parentColumns = arrayOf("id"),
////            childColumns = arrayOf("productId"),
////            onDelete = ForeignKey.CASCADE,
////        )
////    ],
////)
//data class ShoppingCartProductDb(
////    val productId: String,
////    val amount: Int,
//
//    val product: String
//)
//
////data class ProductAndAmount(
////    @Embedded
////    val cartProduct: ShoppingCartProductDb,
////    @Relation(
////        parentColumn = "productId",
////        entityColumn = "id",
////    )
////    val product: ProductDb,
////)
