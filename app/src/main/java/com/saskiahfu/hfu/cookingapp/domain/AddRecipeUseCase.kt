package com.saskiahfu.hfu.cookingapp.domain
//
//import com.saskiahfu.hfu.cookingapp.data.RecipeRepository
//import com.saskiahfu.hfu.cookingapp.data.network.AddItemRequestDto
//import com.saskiahfu.hfu.cookingapp.data.network.AddRecipeRequestDto
//import com.saskiahfu.hfu.cookingapp.data.network.WebService
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//import java.util.*
//import javax.inject.Inject
//
//class AddRecipeUseCase @Inject constructor(
//    private val webService: WebService,
//    private val recipeRepository: RecipeRepository
//) {
//
//    suspend operator fun invoke(
//        id: String,
//        name: String,
//        category: String,
////        img: String,
////        ingredients: String,
////        steps: String,
//        sourceName: String,
//        sourceUri: String
//    ) =
//        withContext(Dispatchers.Default) {
//
//            webService.addRecipe(
//                AddRecipeRequestDto(
//                    id,
//                    name,
////                    img,
////                    ingredients,
////                    steps,
//                    category,
//                    sourceName,
//                    sourceUri
//                ),
//            )
//
//            return@withContext true
//        }
//}