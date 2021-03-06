package hfu.cookingapp.com.routes

import hfu.cookingapp.com.model.userDao
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.auth.UserIdPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route

fun Route.loginRouting() {
    route("/v1/user") {
        get {
            call.principal<UserIdPrincipal>()?.name?.let { userId ->
                userDao.userById(userId)?.let { userId ->
                    call.respond(LoginResponse(userId.id))
                } ?: call.respond(HttpStatusCode.Unauthorized)
            } ?: call.respond(HttpStatusCode.Unauthorized)
        }
    }
}

@kotlinx.serialization.Serializable
data class LoginResponse(
    val id: String,
)
