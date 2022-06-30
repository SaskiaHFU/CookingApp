package com.saskiahfu.hfu.cookingapp.domain

import com.saskiahfu.hfu.cookingapp.data.RecipeCategoryRepository
import com.saskiahfu.hfu.cookingapp.data.network.AddRecipeCatRequestDto
import com.saskiahfu.hfu.cookingapp.data.network.WebService
import com.saskiahfu.hfu.cookingapp.domain.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class AddCategoryUseCase @Inject constructor(
    private val webService: WebService,
    private val recipeCategoryRepository: RecipeCategoryRepository
) {

    suspend operator fun invoke(
        name: String,
    ) =
        withContext(Dispatchers.Default) {

            webService.addRecipeCategory(
                AddRecipeCatRequestDto(
                    name,
                ),
            )

            RecipeCategory.create(
                RecipeCategoryId(UUID.randomUUID().toString()),
                name,

                )
                ?.let { recipeCategoryRepository.addRecipeCat(it) }

            return@withContext true
        }
}