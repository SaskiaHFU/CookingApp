package com.saskiahfu.hfu.cookingapp.feature.cart.ui.old

//import android.content.Context
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.saskiahfu.hfu.cookingapp.data.network.WebService
//import com.saskiahfu.hfu.cookingapp.domain.observeUseCase.ObserveShoppingCartUseCase
//import com.saskiahfu.hfu.cookingapp.domain.PayShoppingCartUseCase
//import com.saskiahfu.hfu.cookingapp.domain.model.ProductIcon
//import com.saskiahfu.hfu.cookingapp.domain.model.ShoppingCart
//import com.saskiahfu.hfu.cookingapp.feature.getProductIconResource
//import dagger.hilt.android.lifecycle.HiltViewModel
//import javax.inject.Inject
//import kotlinx.coroutines.launch
//
//@HiltViewModel
//class ShoppingCartViewModel @Inject constructor(
//    private val observeShoppingCart: ObserveShoppingCartUseCase,
//    private val payShoppingCart: PayShoppingCartUseCase,
//) : ViewModel() {
//
//    fun bindUI(context: Context): LiveData<ShoppingCartScreenUI> = observeShoppingCart().map { cart ->
//        when (cart) {
//            is ShoppingCart.Empty -> ShoppingCartScreenUI(
//                products = emptyList(),
//                totalCost = 0.0,
//            )
//            is ShoppingCart.Unpayed -> ShoppingCartScreenUI(
//                products = cart.products.entries.map { (buyableProduct, amount) ->
//                    ShoppingCartScreenProductUI(
//                        id = buyableProduct.product.id,
//                        name = buyableProduct.product.name,
//                        description = buyableProduct.product.description,
//                        amount = amount,
//                        totalCost = buyableProduct.price * amount,
//                        icon = (buyableProduct.product.icon as? ProductIcon.Local)?.let { getProductIconResource(buyableProduct, context) },
//                        iconUrl = (buyableProduct.product.icon as? ProductIcon.Remote)?.let { "${WebService.BASE_URL}${it.url}" },
//                    )
//                }.sortedBy { it.name },
//                totalCost = cart.totalCost,
//            )
//        }
//    }.asLiveData()
//
//    fun onPayCart() {
//        viewModelScope.launch {
//            payShoppingCart()
//        }
//    }
//}
