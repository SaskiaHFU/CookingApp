package com.saskiahfu.hfu.cookingapp.data.database.links

import com.saskiahfu.hfu.cookingapp.domain.model.*

fun linkCategoryToDb(cat: LinkCategory): LinkCategoryDb = LinkCategoryDb(
    id = cat.id.value,
    name = cat.name,
    links = cat.links
)

fun linkCategoryFromDb(cat: LinkCategoryDb): LinkCategory? {
    return LinkCategory.create(
        id = LinkCategoryId(cat.id),
        name = cat.name,
        links = cat.links
    )
}

