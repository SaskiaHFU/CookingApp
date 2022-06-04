package com.saskiahfu.hfu.cookingapp.feature.calendar.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.saskiahfu.hfu.cookingapp.data.network.WebService
import com.saskiahfu.hfu.cookingapp.domain.BuyProductUseCase
import com.saskiahfu.hfu.cookingapp.domain.observeUseCase.ObserveProductsUseCase
import com.saskiahfu.hfu.cookingapp.domain.model.ProductIcon
import com.saskiahfu.hfu.cookingapp.domain.model.ProductId
import com.saskiahfu.hfu.cookingapp.feature.getProductIconResource
import com.saskiahfu.hfu.cookingapp.feature.products.ui.ProductUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val observeProducts: ObserveProductsUseCase,
    private val buyProduct: BuyProductUseCase,
) : ViewModel() {
    fun bindUi(context: Context): LiveData<List<ProductUI>> =
        observeProducts().map {
            it.map { buyableProduct ->
                ProductUI(
                    id = buyableProduct.product.id,
                    name = buyableProduct.product.name,
                    description = buyableProduct.product.description,
                    icon = (buyableProduct.product.icon as? ProductIcon.Local)?.let { getProductIconResource(buyableProduct, context) },
                    iconUrl = (buyableProduct.product.icon as? ProductIcon.Remote)?.let { "${WebService.BASE_URL}${it.url}" },
                )
            }.sortedBy { it.name }
        }.asLiveData()

    fun onAddProduct(productId: ProductId) {
        viewModelScope.launch {
            buyProduct(productId)
        }
    }
}