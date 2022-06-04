package com.saskiahfu.hfu.cookingapp.data

import com.saskiahfu.hfu.cookingapp.data.database.ProductDao
import com.saskiahfu.hfu.cookingapp.data.database.buyableProductFromDb
import com.saskiahfu.hfu.cookingapp.data.database.buyableProductToDb
import com.saskiahfu.hfu.cookingapp.data.database.links.LinksCategoryDao
import com.saskiahfu.hfu.cookingapp.data.database.links.linkCategoryFromDb
import com.saskiahfu.hfu.cookingapp.data.database.links.linkCategoryToDb
import com.saskiahfu.hfu.cookingapp.data.database.productFromDb
import com.saskiahfu.hfu.cookingapp.domain.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LinksCategoryRepository @Inject constructor(
    private val dao: LinksCategoryDao,
) {
    suspend fun getAllLinkCatgories(): List<LinkCategory> =
        dao.getAll().mapNotNull { linkCategoryFromDb(it) }

    fun observeAllLinkCategories(): Flow<List<LinkCategory>> =
        dao.observeAll().map { it.mapNotNull(::linkCategoryFromDb) }

    suspend fun getLinkCategoryById(id: LinkCategoryId): LinkCategory? =
        dao.getById(id.value)?.let { linkCategoryFromDb(it) }

    suspend fun addLinkCategory(cat: LinkCategory) {
        dao.insert(
            linkCategoryToDb(cat)
        )
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }
}