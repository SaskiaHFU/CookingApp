package com.saskiahfu.hfu.cookingapp.domain.downloadUseCase

import android.util.Log
import com.saskiahfu.hfu.cookingapp.data.RecipeCategoryRepository
import com.saskiahfu.hfu.cookingapp.data.network.WebService
import com.saskiahfu.hfu.cookingapp.domain.model.RecipeCategory
import com.saskiahfu.hfu.cookingapp.domain.model.RecipeCategoryId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DownloadRecipeCategoriesUseCase @Inject constructor(
    private val webService: WebService,
    private val recipeCategoryRepository: RecipeCategoryRepository,
) {
    suspend operator fun invoke() = withContext(Dispatchers.Default) {
        kotlin.runCatching {
            webService.getRecipeCategories().mapNotNull {
                RecipeCategory.create(
                    RecipeCategoryId(it.id),
                    it.name
                )

            }.forEach {
                recipeCategoryRepository.addRecipeCat(it)
            }
        }
    }.fold(
        { Log.e("RECIPECAT", "ok") },
        { Log.e("RECIPECAT", it.toString(), it) },
    )
}