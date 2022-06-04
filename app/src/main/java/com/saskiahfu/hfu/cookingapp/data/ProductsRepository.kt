package com.saskiahfu.hfu.cookingapp.data

import com.saskiahfu.hfu.cookingapp.data.database.ProductDao
import com.saskiahfu.hfu.cookingapp.data.database.buyableProductFromDb
import com.saskiahfu.hfu.cookingapp.data.database.buyableProductToDb
import com.saskiahfu.hfu.cookingapp.data.database.productFromDb
import com.saskiahfu.hfu.cookingapp.domain.model.BuyableProduct
import com.saskiahfu.hfu.cookingapp.domain.model.Product
import com.saskiahfu.hfu.cookingapp.domain.model.ProductId
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductsRepository @Inject constructor(
    private val dao: ProductDao,
) {
    suspend fun getAllProducts(): List<Product> = dao.getAll().mapNotNull { productFromDb(it) }

    fun observeAllBuyableProduct(): Flow<List<BuyableProduct>> = dao.observeAll().map { it.mapNotNull(::buyableProductFromDb) }

    suspend fun getProductById(id: ProductId): Product? = dao.getById(id.value)?.let { productFromDb(it) }

    suspend fun getBuyableProductById(id: ProductId): BuyableProduct? = dao.getById(id.value)?.let { buyableProductFromDb(it) }

    suspend fun addBuyableProduct(buyableProduct: BuyableProduct) {
        dao.insert(
            buyableProductToDb(buyableProduct)
        )
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }
}
