package com.saskiahfu.hfu.cookingapp.data.database.cart

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.saskiahfu.hfu.cookingapp.data.database.recipe.RecipeCategoryDb
import com.saskiahfu.hfu.cookingapp.domain.model.CartItemId
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Insert
    suspend fun insert(item: CartDb)

    @Query("SELECT * FROM cart")
    suspend fun getAll(): List<CartDb>

    @Query("SELECT * FROM cart")
    fun observeAll(): Flow<List<CartDb>>

    @Query("SELECT * FROM cart WHERE id = :id")
    suspend fun getById(id: String): CartDb?

    @Query("DELETE FROM cart")
    abstract suspend fun deleteAll()

    @Query("DELETE FROM cart WHERE id = :id")
    suspend fun deleteById(id: String)

    @Update
    abstract suspend fun update(product: CartDb)


//    @Transaction
//    open suspend fun upsert(item: ShoppingCartProductDb) {
//        val rowId = insert(item)
//        if (rowId == -1L) {
//            update(item)
//        }
//    }
//
//    @Transaction
//    open suspend fun upsert(items: List<ShoppingCartProductDb>) {
//        items.forEach { upsert(it) }
//    }
//
//    @Update
//    abstract suspend fun update(item: ShoppingCartProductDb)
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    abstract suspend fun insert(item: ShoppingCartProductDb): Long
//
//    @Query("DELETE FROM shopping_cart_product")
//    abstract suspend fun deleteAll()
//
//    @Transaction
//    @Query("SELECT * FROM shopping_cart_product")
//    abstract suspend fun getAll(): List<ProductAndAmount>
//
//    @Transaction
//    @Query("SELECT * FROM shopping_cart_product")
//    abstract fun observeAll(): Flow<List<ProductAndAmount>>
}
