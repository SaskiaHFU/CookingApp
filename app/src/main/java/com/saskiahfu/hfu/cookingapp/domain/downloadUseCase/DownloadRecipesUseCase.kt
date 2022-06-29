package com.saskiahfu.hfu.cookingapp.domain.downloadUseCase

import android.util.Log
import com.saskiahfu.hfu.cookingapp.data.RecipeRepository
import com.saskiahfu.hfu.cookingapp.data.network.WebService
import com.saskiahfu.hfu.cookingapp.domain.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DownloadRecipesUseCase @Inject constructor(
    private val webService: WebService,
    private val recipeRepository: RecipeRepository,
) {
    suspend operator fun invoke() = withContext(Dispatchers.Default) {
        kotlin.runCatching {
            webService.getRecipes().mapNotNull {
                Recipe.create(
                    RecipeId(it.id),
                    it.name,
                    img = RecipeImg.Remote(it.img_url),
                    it.ingredients,
                    it.steps,
                    it.category,
                    it.sourceName,
                    it.sourceUri
                )

            }.forEach {
                recipeRepository.addRecipe(it)
            }
        }
    }.fold(
        { Log.e("RECIPES", "ok") },
        { Log.e("RECIPES", it.toString(), it) },
    )
}