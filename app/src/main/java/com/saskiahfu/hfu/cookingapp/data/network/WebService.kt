package com.saskiahfu.hfu.cookingapp.data.network


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface WebService {

    //    User
    @POST("v1/user")
    suspend fun signUp(@Body request: SignUpRequestDto): SignUpResponseDto

    @GET("v1/user")
    suspend fun login(): LoginResponseDto

    //    Products
    @GET("v1/product")
    suspend fun getProducts(): List<ProductDto>

    @PUT("v1/cart/{id}")
    suspend fun addItem(@Path("id") id: String, @Body body: AddItemRequestDto)


    //Cart
    @GET("v1/cart/{id}")
    suspend fun getCartById(@Path("id") id: String): List<ShoppingCartItemDto>

    @DELETE("v1/cart/{id}")
    suspend fun clearCart(@Path("id") id: String)

    //    Recipes
    @GET("v1/recipes")
    suspend fun getRecipes(): List<RecipesDto>

//    @POST("v1/recipes")
//    suspend fun addRecipe(@Body request: AddRecipeRequestDto)

    //    Mealplan
    @GET("v1/mealplan")
    suspend fun getMeals(): List<MealsDto>

//    @PUT("v1/mealplan")
//    suspend fun addMeal(@Path("day") day: String, @Body body: AddMealToPlanRequestDto)


    companion object {
        const val BASE_URL = "http://10.0.2.2:8080/"
    }
}

//Login
@Serializable
data class LoginResponseDto(
    val cartId: String,
)

//Cart
@Serializable
data class ShoppingCartItemDto(
    val id: String,
    val productId: String,
    val cartId: String,
)

//Product
@Serializable
data class AddItemRequestDto(
    val productId: String,
)

@Serializable
data class ProductDto(
    val id: String,
    val name: String,
    val description: String,
    @SerialName("icon")
    val imageUrl: String,
    val price: Double,
)


//Signup
@Serializable
data class SignUpRequestDto(
    val userName: String,
    val password: String,
)

@Serializable
data class SignUpResponseDto(
    val cartId: String,
)

//Recipes
@Serializable
data class RecipesDto(
    val id: String,
    val name: String,
//    val img: String,
//    val ingredients: String,
//    val steps: String,
//    val category: String,
    val sourceName: String,
    val sourceUri: String
)


//@Serializable
//data class AddRecipeRequestDto(
//    val id: String,
//    val name: String,
////    val img: String,
////    val ingredients: String,
////    val steps: String,
//    val category: String,
//    val sourceName: String,
//    val sourceUri: String
//)

//@Serializable
//data class AddRecipeCategoryRequestDto(
//    val id: String,
//    val name: String,
//    val recipes: String,
//)

//@Serializable
//data class AddMealToPlanRequestDto(
//    val day: String,
//    val bfName: String,
//    val luName: String,
//    val diName: String,
//)

@Serializable
data class MealsDto(
    val day: String,
    val bfName: String,
    val luName: String,
    val diName: String,
)

