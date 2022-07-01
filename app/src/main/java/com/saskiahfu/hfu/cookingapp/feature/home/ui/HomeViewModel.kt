package com.saskiahfu.hfu.cookingapp.feature.home.ui

//import android.content.Context
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.asLiveData
//import androidx.lifecycle.viewModelScope
//import com.saskiahfu.hfu.cookingapp.domain.observeUseCase.ObserveRecipesUseCase
//import com.saskiahfu.hfu.cookingapp.feature.recipes.ui.RecipeUI
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.map
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//class HomeViewModel @Inject constructor(
//    private val observeRecipes: ObserveRecipesUseCase,
//
//) : ViewModel() {
//    fun bindUi(context: Context): LiveData<List<RecipeUI>> =
//        observeRecipes().map {
//            it.map {
//                RecipeUI(
//                    name = it.name,
//                    img =
//                )
//            }.sortedBy { it.name }
//        }.asLiveData()
//
//    fun onAddProduct(productId: ProductId) {
//        viewModelScope.launch {
////            buyProduct(productId)
//        }
//    }
//}