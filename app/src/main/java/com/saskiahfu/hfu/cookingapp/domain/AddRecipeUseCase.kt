package com.saskiahfu.hfu.cookingapp.domain

import com.saskiahfu.hfu.cookingapp.data.RecipeRepository
import com.saskiahfu.hfu.cookingapp.data.network.AddRecipeRequestDto
import com.saskiahfu.hfu.cookingapp.data.network.WebService
import com.saskiahfu.hfu.cookingapp.domain.model.ProductIcon
import com.saskiahfu.hfu.cookingapp.domain.model.Recipe
import com.saskiahfu.hfu.cookingapp.domain.model.RecipeId
import com.saskiahfu.hfu.cookingapp.domain.model.RecipeImg
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class AddRecipeUseCase @Inject constructor(
    private val webService: WebService,
    private val recipeRepository: RecipeRepository
) {

    suspend operator fun invoke(
        name: String,
        img: String,
        ingredients: String,
        steps: String,
        category: String,
        sourceName: String,
        sourceUri: String
    ) =
        withContext(Dispatchers.Default) {

            webService.addRecipe(
                AddRecipeRequestDto(
                    name,
                    img,
                    ingredients,
                    steps,
                    category,
                    sourceName,
                    sourceUri
                ),
            )

            Recipe.create(
                RecipeId(UUID.randomUUID().toString()),
                name,
                RecipeImg.Remote(img),
                ingredients,
                steps,
                category,
                sourceName,
                sourceUri
            )
                ?.let { recipeRepository.addRecipe(it) }

            return@withContext true
        }
}