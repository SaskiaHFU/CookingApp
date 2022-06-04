package com.saskiahfu.hfu.cookingapp.data.database.links

import com.saskiahfu.hfu.cookingapp.domain.model.*

fun linkToDb(link: Link): LinksDb = LinksDb(
    id = link.id.value,
    name = link.name,
    category = link.category,
    uri = link.uri,
)

fun linkFromDb(link: LinksDb): Link? {
    return Link.create(
        id = LinkId(link.id),
        name = link.name,
        category = link.category,
        uri = link.uri,
    )
}