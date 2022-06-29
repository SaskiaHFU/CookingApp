package hfu.cookingapp.com.routes


import hfu.cookingapp.com.model.RecipeItem
import hfu.cookingapp.com.model.RecipeItems
import hfu.cookingapp.com.model.recipeDao
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

@kotlinx.serialization.Serializable
private data class AddRecipeItemRequest(
    val name: String,
    val img: String,
    val ingredients: String,
    val steps: String,
    val category: String,
    val sourceName: String,
    val sourceUri: String
)

fun Route.recipeRouting() {
    route("/v1/recipes") {
        get {
            call.respond(recipes)
        }

        post {
            val recipeData = call.receive<AddRecipeItemRequest>()
            if (recipeData.name.isBlank()
                || recipeData.img.isBlank()
                || recipeData.ingredients.isEmpty() || recipeData.steps.isEmpty()
                || recipeData.category.isBlank()
            ) {
                call.respond(HttpStatusCode.BadRequest)
            } else {
                recipeDao.addRecipeItem(
                    UUID.randomUUID().toString(),
                    recipeData.name,
                    recipeData.img,
                    recipeData.ingredients,
                    recipeData.steps,
                    recipeData.category,
                    recipeData.sourceName,
                    recipeData.sourceUri,
                )?.let { _ ->
                    call.respond(HttpStatusCode.OK)
                } ?: call.respond(HttpStatusCode.BadRequest)
            }
        }
        //TODO eigentlich noch put wenn man was ändert
    }
}
val prefixImg = "productimage/"

val recipes = listOf(
    RecipeItem(
        id = "0",
        name = "Buddah Bowl",
        img = prefixImg + "bowl_png.png",
        category = "Breakfast",
        ingredients = "Vegetables",
        steps = "Cook",
        sourceName = "Kochkarussell",
        sourceUri = "https://kochkarussell.com/"
    ),
    RecipeItem(
        id = "1",
        name = "Buddah Bowl 1",
        img = prefixImg + "recipe.jpg",
        category = "Lunch",
        ingredients = "Vegetables",
        steps = "Cook",
        sourceName = "Kochkarussell",
        sourceUri = "https://kochkarussell.com/"
    ),
    RecipeItem(
        id = "2",
        name = "Buddah Bowl 2",
        img = prefixImg + "recipe.jpg",
        category = "Dinner",
        ingredients = "Vegetables",
        steps = "Cook",
        sourceName = "Hello Fresh",
        sourceUri = "https://www.hellofresh.de/recipes/kasespatzlepfanne-mit-bacon-6283a8344b76dfd4600df29c"
    )
)