package hfu.cookingapp.com.routes

import hfu.cookingapp.com.model.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

@kotlinx.serialization.Serializable
private data class AddRecipeCatRequest(
    val name: String,
)

fun Route.recipeCatRouting() {
    route("/v1/recipeCategory") {
        get {
            call.respond(recipe_categories)
        }

        post {
            val recipeCatData = call.receive<AddRecipeCatRequest>()
            if (recipeCatData.name.isBlank()) {
                call.respond(HttpStatusCode.BadRequest)
            } else {
                recipeCatDao.addRecipeCatItem(
                    UUID.randomUUID().toString(),
                    recipeCatData.name,
                )?.let { _ ->
                    call.respond(HttpStatusCode.OK)
                } ?: call.respond(HttpStatusCode.InternalServerError)
            }
        }

//        delete {
//            ifAuthorized { name ->
//                shoppingCartItemDao.deleteAllByCartId(cartId)
//                call.respond(HttpStatusCode.OK)
//            }
    }
}

val recipe_categories = listOf(
    RecipeCategory(
        id = "0",
        name = "Breakfast"
    ),
    RecipeCategory(
        id = "1",
        name = "Lunch"
    ),
    RecipeCategory(
        id = "3",
        name = "Dinner"
    ),
    RecipeCategory(
        id = "4",
        name = "Kids"
    ),
    RecipeCategory(
        id = "5",
        name = "Drinks"
    ),
)
