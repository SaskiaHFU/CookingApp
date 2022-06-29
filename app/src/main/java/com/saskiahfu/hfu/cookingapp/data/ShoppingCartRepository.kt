package com.saskiahfu.hfu.cookingapp.data

import com.saskiahfu.hfu.cookingapp.data.database.cart.*
import com.saskiahfu.hfu.cookingapp.domain.model.*
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class CartRepository @Inject constructor(
    private val dao: CartDao,
) {
    suspend fun getAllCartItems(): List<CartItem> = dao.getAll().mapNotNull { cartItemFromDb(it)}

    fun observeAllCartItems(): Flow<List<CartItem>> =
        dao.observeAll().map { it.mapNotNull(::cartItemFromDb) }

    suspend fun getCartItemById(id: CartItemId): CartItem? =
        dao.getById(id.value)?.let { cartItemFromDb(it) }

    suspend fun addCartItem(item: CartItem) {
        dao.insert(
            cartItemToDb(item)
        )
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }
}



//class ShoppingCartRepository @Inject constructor(
//    private val userSettingsRepository: UserSettingsRepository,
//    private val dao: ShoppingCartDao,
//) {
//    private suspend fun getId() = userSettingsRepository.getSettings().cartId
//
//    suspend fun getCurrentCart(): ShoppingCart = shoppingCartFromDb(getId(), dao.getAll())
//
//    fun observeCurrentCart(): Flow<ShoppingCart> = dao.observeAll().map {
//        shoppingCartFromDb(getId(), it)
//    }
//
//    suspend fun updateCart(newCart: ShoppingCart): ShoppingCart {
//        when (newCart) {
//            is ShoppingCart.Empty -> dao.deleteAll()
////            is ShoppingCart.Unpayed -> {
////                val items = newCart.products.map { (product, amount) ->
////                    ShoppingCartProductDb(product.product.id.value, amount)
////                }
////                dao.upsert(items)
////            }
//
//
//
//        }
//        return newCart
//    }
//
//    suspend fun deleteAll() {
//        dao.deleteAll()
//    }
//}
