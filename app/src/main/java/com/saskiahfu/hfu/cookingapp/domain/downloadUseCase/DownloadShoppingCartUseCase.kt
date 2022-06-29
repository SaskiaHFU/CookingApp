package com.saskiahfu.hfu.cookingapp.domain.downloadUseCase

import android.util.Log
import com.saskiahfu.hfu.cookingapp.data.*
import com.saskiahfu.hfu.cookingapp.data.network.WebService
import com.saskiahfu.hfu.cookingapp.domain.model.*
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DownloadCartUseCase @Inject constructor(
    private val webService: WebService,
    private val cartRepository: CartRepository,
) {
    suspend operator fun invoke() = withContext(Dispatchers.Default) {
        kotlin.runCatching {
            webService.getCart().mapNotNull {
                CartItem.create(
                    CartItemId(it.id),
                    it.item
                )

            }.forEach {
                cartRepository.addCartItem(it)
            }
        }
    }.fold(
        { Log.e("CART", "ok") },
        { Log.e("CART", it.toString(), it) },
    )
}






//class DownloadShoppingCartUseCase @Inject constructor(
//    private val webService: WebService,
//    private val userSettingsRepository: UserSettingsRepository,
//    private val productsRepository: ProductsRepository,
//    private val shoppingCartRepository: ShoppingCartRepository,
//) {
//
//    suspend operator fun invoke() = withContext(Dispatchers.Default) {
//        kotlin.runCatching {
//            val cartId = userSettingsRepository.getSettings().cartId
//            val response = webService.getCartById(cartId.value)
//            val groups = response.groupingBy {
//                it.productId
//            }
//            val itemAndCount = groups.eachCount()
//
//            if (itemAndCount.isEmpty()) {
//                val cart = ShoppingCart.Empty(cartId)
//                shoppingCartRepository.updateCart(cart)
//            } else {
//                val products = itemAndCount.mapKeys { (productId, _) ->
//                    productsRepository.getBuyableProductById(ProductId(productId))
//                }.filterKeys { it != null }.mapKeys { it.key!! }
//                val cart = ShoppingCart.Unpayed.create(
//                    cartId,
//                    products,
//                    products.entries.sumOf { it.key.price * it.value }
//                )
//                shoppingCartRepository.updateCart(cart)
//            }
//        }
//    }
//}
