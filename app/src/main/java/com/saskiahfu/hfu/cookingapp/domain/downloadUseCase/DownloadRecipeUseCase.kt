package com.saskiahfu.hfu.cookingapp.domain.downloadUseCase

import com.saskiahfu.hfu.cookingapp.data.RecipeRepository
import com.saskiahfu.hfu.cookingapp.data.network.WebService
import javax.inject.Inject

class DownloadRecipeUseCase @Inject constructor(
    private val webService: WebService,
    private val recipeRepository: RecipeRepository,
) {

}