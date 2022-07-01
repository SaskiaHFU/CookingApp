package com.saskiahfu.hfu.cookingapp.feature.cart.ui

import android.content.Context
import androidx.lifecycle.*
import com.saskiahfu.hfu.cookingapp.data.CartRepository
import com.saskiahfu.hfu.cookingapp.domain.AddItemToCartUseCase
import com.saskiahfu.hfu.cookingapp.domain.DeleteCartItemsUseCase
import com.saskiahfu.hfu.cookingapp.domain.model.CartItemId
import com.saskiahfu.hfu.cookingapp.domain.observeUseCase.ObserveCartUseCase
import com.saskiahfu.hfu.cookingapp.domain.observeUseCase.ObserveRecipeCategoriesUseCase
import com.saskiahfu.hfu.cookingapp.feature.recipes.ui.RecipeUI
import com.saskiahfu.hfu.cookingapp.feature.recipes.ui.categories.RecipeCategoryUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val observeCart: ObserveCartUseCase,
    private val addItem: AddItemToCartUseCase,
    private val cartRepository: CartRepository,
    private val deleteCart: DeleteCartItemsUseCase,
) : ViewModel() {


    fun bindUi(context: Context): LiveData<List<CartItemUI>> =
        observeCart().map {
            it.map { cartItem ->
                CartItemUI(
                    id = cartItem.id,
                    item = cartItem.item,
                )
            }
        }.asLiveData()

    fun onAddCartItem(
        item: String
    ) {
        viewModelScope.launch {
            addItem(item)
        }
    }


    fun onDeleteCart() {
        viewModelScope.launch {
            val cart = cartRepository
            cart.deleteAll()
        }
    }

    fun onDeleteCartItem(id: CartItemId) {
        viewModelScope.launch {
            val cart = cartRepository
            cart.deleteById(id)
        }
    }
}