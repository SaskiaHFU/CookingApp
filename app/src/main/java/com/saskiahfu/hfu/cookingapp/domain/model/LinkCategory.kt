package com.saskiahfu.hfu.cookingapp.domain.model

@JvmInline
value class LinkCategoryId(val value: String)

class LinkCategory private constructor(
    val id: LinkCategoryId,
    val name: String,
    val links: String,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LinkCategory

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    companion object {
        fun create(
            id: LinkCategoryId,
            name: String,
            links: String,

            ): LinkCategory? {
            if (name.isBlank()) return null
            return LinkCategory(id, name, links)
        }
    }
}