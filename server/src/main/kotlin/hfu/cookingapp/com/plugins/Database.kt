package hfu.cookingapp.com.plugins

import hfu.cookingapp.com.model.DatabaseFactory
import io.ktor.server.application.Application

fun Application.configureDatabase() {
    DatabaseFactory.init()
}
