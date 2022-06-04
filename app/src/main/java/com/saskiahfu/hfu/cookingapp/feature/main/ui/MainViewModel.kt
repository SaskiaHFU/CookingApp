package com.saskiahfu.hfu.cookingapp.feature.main.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.saskiahfu.hfu.cookingapp.domain.observeUseCase.ObserveShoppingCartUseCase
import com.saskiahfu.hfu.cookingapp.domain.model.ShoppingCart

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.map

@HiltViewModel
class MainViewModel @Inject constructor(
    private val observeShoppingCart: ObserveShoppingCartUseCase,
) : ViewModel() {
    fun bindUi(): LiveData<Int> = observeShoppingCart()
        .map { cart ->
            when (cart) {
                is ShoppingCart.Empty -> 0
                is ShoppingCart.Unpayed -> cart.products.entries.sumOf { it.value }

            }
        }
        .asLiveData()
}
