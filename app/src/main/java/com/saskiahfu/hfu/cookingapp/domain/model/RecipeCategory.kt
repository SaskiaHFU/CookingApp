package com.saskiahfu.hfu.cookingapp.domain.model


@JvmInline
value class RecipeCategoryId(val value: String)

class RecipeCategory private constructor(
    val id: RecipeCategoryId,
    val name: String,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as RecipeCategory
        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    companion object {
        fun create(
            id: RecipeCategoryId,
            name: String,
        ): RecipeCategory? {
            if (name.isBlank()) return null
            return RecipeCategory(id, name)
        }
    }

}