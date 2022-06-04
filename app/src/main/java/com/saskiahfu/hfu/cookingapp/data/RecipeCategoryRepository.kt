package com.saskiahfu.hfu.cookingapp.data
//
//import com.saskiahfu.hfu.cookingapp.data.database.recipe.RecipeCategoryDao
//import com.saskiahfu.hfu.cookingapp.data.database.recipe.recipeCatFromDb
//import com.saskiahfu.hfu.cookingapp.data.database.recipe.recipeCatToDb
//import com.saskiahfu.hfu.cookingapp.domain.model.BuyableProduct
//import com.saskiahfu.hfu.cookingapp.domain.model.RecipeCategory
//import com.saskiahfu.hfu.cookingapp.domain.model.RecipeCategoryId
//import javax.inject.Inject
//
//class RecipeCategoryRepository @Inject constructor(
//    private val dao: RecipeCategoryDao,
//) {
//    suspend fun getAllRecipeCategories(): List<RecipeCategory> =
//        dao.getAll().mapNotNull { recipeCatFromDb(it) }
//
//    suspend fun getRecipeCatById(id: RecipeCategoryId): RecipeCategory? =
//        dao.getById(id.value)?.let { recipeCatFromDb(it) }
//
//    suspend fun addRecipeCat(recipeCat: RecipeCategory) {
//        dao.insert(
//            recipeCatToDb(recipeCat)
//        )
//    }
//}