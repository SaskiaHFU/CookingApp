package hfu.cookingapp.com.plugins

import hfu.cookingapp.com.routes.*
import io.ktor.server.application.Application
import io.ktor.server.auth.authenticate
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    routing {
        assets()
        signupRouting()
        addMealToPlanRouting()
        recipeRouting()
        recipeCatRouting()
        cartRouting()

        authenticate("auth-basic") {
            loginRouting()
            productRouting()
        }
    }
}
