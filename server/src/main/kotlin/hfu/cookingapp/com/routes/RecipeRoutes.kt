package hfu.cookingapp.com.routes


import hfu.cookingapp.com.model.recipeDao
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

@kotlinx.serialization.Serializable
private data class AddRecipeItemRequest(
    val recipeId: String,
    val name: String,
//    val img: String,
//    val ingredients: List<String>,
//    val steps: List<String>,
//    val category: String,
    val sourceName: String,
    val sourceUri: String
)

fun Route.recipeRouting() {
    route("/v1/recipe/") {
        get {
            ifAuthorized { recipeId ->
                call.respond(
                    recipeDao.itemsByRecipeId(recipeId)
                )
            }
        }

        post {

            val recipeData = call.receive<AddRecipeItemRequest>()
            if (recipeData.name.isBlank()
//                || recipeData.img.isBlank()
//                || recipeData.ingredients.isEmpty()|| recipeData.steps.isEmpty()
//                || recipeData.category.isBlank()
                )
              {
                call.respond(HttpStatusCode.BadRequest)
            } else {
                recipeDao.addRecipeItem(
                    UUID.randomUUID().toString(),
                    recipeData.name,
//                    recipeData.img,
//                    recipeData.ingredients,
//                    recipeData.steps,
//                    recipeData.category,
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