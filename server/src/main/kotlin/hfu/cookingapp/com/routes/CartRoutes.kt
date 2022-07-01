package hfu.cookingapp.com.routes

import hfu.cookingapp.com.model.CartItem
import hfu.cookingapp.com.model.cartDao
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.UUID

@kotlinx.serialization.Serializable
private data class AddCartItemRequest(
    val item: String,
)

@kotlinx.serialization.Serializable
private data class DeleteCartItemRequest(
    val id: String,
)

fun Route.cartRouting() {

    route("/v1/cart") {

        get {
            call.respond(cart)
        }

        post {
            val cartData = call.receive<AddCartItemRequest>()
            if (cartData.item.isBlank()) {
                call.respond(HttpStatusCode.BadRequest)
            } else {
                cartDao.addCartItem(
                    UUID.randomUUID().toString(),
                    cartData.item
                )?.let { _ ->
                    call.respond(HttpStatusCode.OK)
                } ?: call.respond(HttpStatusCode.InternalServerError)
            }
        }

        delete {
            cartDao.deleteAll()
        }
    }

    route("/v1/cart/{id}") {
        delete {
            val id = call.receive<DeleteCartItemRequest>()
            cartDao.deleteAllByCartId(
                id.id
            ).let { _ ->
                call.respond(HttpStatusCode.OK)
            }

        }
    }
}

val cart = listOf(
    CartItem(
        id = "0",
        item = "Karotte"
    ),
    CartItem(
        id = "1",
        item = "Gurke"
    ),
    CartItem(
        id = "2",
        item = "Reis"
    ),
    CartItem(
        id = "3",
        item = "Salat"
    ),
)