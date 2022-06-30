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

    @GET("v1/cart")
    suspend fun getCart(): List<CartItemDto>

    @DELETE("v1/cart")
    suspend fun clearCart(@Path("id") id: String)

    @POST("v1/cart")
    suspend fun addCartItem(@Body request: AddCartItemRequestDto)

//    @GET("v1/cart/{id}")
//    suspend fun getCartById(@Path("id") id: String): List<ShoppingCartItemDto>
//
//    @DELETE("v1/cart/{id}")
//    suspend fun clearCart(@Path("id") id: String)

    //    Recipes
    @GET("v1/recipes")
    suspend fun getRecipes(): List<RecipesDto>

    @GET("v1/recipeCategory")
    suspend fun getRecipeCategories(): List<RecipeCategoryDto>

    @POST("v1/recipes")
    suspend fun addRecipe(@Body request: AddRecipeRequestDto)

    @POST("v1/recipeCategory")
    suspend fun addRecipeCategory(@Body request: AddRecipeCatRequestDto)

    //    Mealplan
    @GET("v1/mealplan")
    suspend fun getMeals(): List<MealsDto>

    @PUT("v1/mealplan/{day}")
    suspend fun updateMeal(@Path("day") day: String, @Body body: AddMealToPlanRequestDto)


    companion object {
        const val BASE_URL = "http://10.0.2.2:8080/"
    }
}

//Login
@Serializable
data class LoginResponseDto(
    val id: String,
)

//Cart
@Serializable
data class CartItemDto(
    val id: String,
    val item: String,
)

@Serializable
data class AddCartItemRequestDto(
    val item: String,
)

//@Serializable
//data class ShoppingCartItemDto(
//    val id: String,
//    val productId: String,
//    val cartId: String,
//)

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
    val userName: String,
)

//Recipes
@Serializable
data class RecipesDto(
    val id: String,
    val name: String,
    @SerialName("img")
    val img_url: String,
    val ingredients: String,
    val steps: String,
    val category: String,
    val sourceName: String,
    val sourceUri: String
)


@Serializable
data class AddRecipeRequestDto(
    val name: String,
    @SerialName("img")
    val img_url: String,
    val ingredients: String,
    val steps: String,
    val category: String,
    val sourceName: String,
    val sourceUri: String
)

@Serializable
data class RecipeCategoryDto(
    val id: String,
    val name: String,
)

@Serializable
data class AddRecipeCatRequestDto(
    val name: String,
)

@Serializable
data class AddMealToPlanRequestDto(
    val day: String,
    val bfName: String,
    val luName: String,
    val diName: String,
)

@Serializable
data class MealsDto(
    val day: String,
    val bfName: String,
    val luName: String,
    val diName: String,
)

