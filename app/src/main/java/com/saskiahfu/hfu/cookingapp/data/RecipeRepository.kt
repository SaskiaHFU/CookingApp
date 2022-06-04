package com.saskiahfu.hfu.cookingapp.data


import com.saskiahfu.hfu.cookingapp.data.database.recipe.RecipeDao
import com.saskiahfu.hfu.cookingapp.data.database.recipe.recipeFromDb
import com.saskiahfu.hfu.cookingapp.data.database.recipe.recipeToDb
import com.saskiahfu.hfu.cookingapp.domain.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val dao: RecipeDao,
) {
    suspend fun getAllRecipes(): List<Recipe> = dao.getAll().mapNotNull { recipeFromDb(it) }

    fun observeAllRecipes(): Flow<List<Recipe>> =
        dao.observeAll().map { it.mapNotNull(::recipeFromDb) }

    suspend fun getRecipeById(id: RecipeId): Recipe? =
        dao.getById(id.value)?.let { recipeFromDb(it) }

    suspend fun addRecipe(recipe: Recipe) {
        dao.insert(
            recipeToDb(recipe)
        )
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }
}