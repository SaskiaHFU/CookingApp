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
    }
}

val recipe_categories = listOf(
    RecipeCategory(
        id = "a59c0e7b-3a58-4859-934d-1a0393831637",
        name = "Breakfast"
    ),
    RecipeCategory(
        id = "867e5af2-aa53-4e46-9cfd-a1bc9b2929cb",
        name = "Lunch"
    ),
    RecipeCategory(
        id = "f16cdf15-6528-4a0b-993c-24d5bf8007a7",
        name = "Dinner"
    ),
    RecipeCategory(
        id = "f16cdf15-bb45-4a0b-993c-24d5bf8007a7",
        name = "Kids"
    ),

)
