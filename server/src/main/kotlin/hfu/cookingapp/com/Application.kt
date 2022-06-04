package hfu.cookingapp.com

import hfu.cookingapp.com.plugins.configureDatabase
import hfu.cookingapp.com.plugins.configureRouting
import hfu.cookingapp.com.plugins.configureSecurity
import hfu.cookingapp.com.plugins.configureSerialization
import hfu.cookingapp.com.plugins.configureSlowResponses
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        configureDatabase()
        configureRouting()
        configureSecurity()
        configureSerialization()
        configureSlowResponses()
    }.start(wait = true)
}
