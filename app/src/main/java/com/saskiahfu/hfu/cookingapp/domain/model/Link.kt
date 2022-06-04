package com.saskiahfu.hfu.cookingapp.domain.model


@JvmInline
value class LinkId(val value: String)

class Link private constructor(
    val id: LinkId,
    val name: String,
    val category: String,
    val uri: String,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Link

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    companion object {
        fun create(
            id: LinkId,
            name: String,
            category: String,
            uri: String,
        ): Link? {
            if (name.isBlank()) return null
            return Link(id, name, category, uri)
        }
    }
}