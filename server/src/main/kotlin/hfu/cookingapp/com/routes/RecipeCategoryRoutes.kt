package hfu.cookingapp.com.routes

import hfu.cookingapp.com.model.recipeCatDao
import hfu.cookingapp.com.model.shoppingCartItemDao
import hfu.cookingapp.com.model.userDao
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

@kotlinx.serialization.Serializable
private data class AddRecipeCatRequest(
    val id: String,
    val name: String,
    val recipes: String
)

fun Route.recipeCatRouting() {
    route("/v1/recipeCategory/{id}") {
//        get {
//            call.respond(
//                recipeCatDao.itemsByCatId(id)
//            )
//        }

        post {
            val recipeCatData = call.receive<AddRecipeCatRequest>()
            if (recipeCatData.name.isBlank()) {
                call.respond(HttpStatusCode.BadRequest)
            } else {
                if (recipeCatDao.itemsByCatId(recipeCatData.name) != null) {
                    call.respond(HttpStatusCode.BadRequest, "Category already exists")
                } else {
                    recipeCatDao.addRecipeCatItem(
                        UUID.randomUUID().toString(),
                        recipeCatData.name,
                        recipeCatData.recipes,
                    )?.let { _ ->
                        call.respond(HttpStatusCode.OK)
                    } ?: call.respond(HttpStatusCode.InternalServerError)
                }
            }
        }

//        delete {
//            ifAuthorized { name ->
//                shoppingCartItemDao.deleteAllByCartId(cartId)
//                call.respond(HttpStatusCode.OK)
//            }
    }
}
