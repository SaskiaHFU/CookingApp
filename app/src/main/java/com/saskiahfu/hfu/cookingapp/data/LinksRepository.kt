package com.saskiahfu.hfu.cookingapp.data

import com.saskiahfu.hfu.cookingapp.data.database.links.LinksDao
import com.saskiahfu.hfu.cookingapp.data.database.links.linkFromDb
import com.saskiahfu.hfu.cookingapp.data.database.links.linkToDb
import com.saskiahfu.hfu.cookingapp.domain.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LinksRepository @Inject constructor(
    private val dao: LinksDao,
) {
    suspend fun getAllLinks(): List<Link> = dao.getAll().mapNotNull { linkFromDb(it) }

    fun observeAllLink(): Flow<List<Link>> = dao.observeAll().map { it.mapNotNull(::linkFromDb) }

    suspend fun getLinkById(id: LinkId): Link? = dao.getById(id.value)?.let { linkFromDb(it) }

    suspend fun addLink(link: Link) {
        dao.insert(
            linkToDb(link)

        )
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }
}