package hfu.cookingapp.com.routes

import hfu.cookingapp.com.model.userDao
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import java.util.UUID

@kotlinx.serialization.Serializable
data class SignupRequest(
    val userName: String,
    val password: String,
)

@kotlinx.serialization.Serializable
data class SignupResponse(
    val userName: String,
)

fun Route.signupRouting() {
    route("/v1/user") {
        post {
            val userData = call.receive<SignupRequest>()
            if (userData.userName.isBlank() || userData.password.isBlank()) {
                call.respond(HttpStatusCode.BadRequest)
            } else {
                if (userDao.userByName(userData.userName) != null) {
                    call.respond(HttpStatusCode.BadRequest, "User already exists")
                } else {
                    userDao.addUser(
                        UUID.randomUUID().toString(),
                        userData.userName,
                        userData.password,
                    )?.let { newUser ->
                        call.respond(SignupResponse(newUser.name))
                    } ?: call.respond(HttpStatusCode.InternalServerError)
                }
            }
        }
    }
}
