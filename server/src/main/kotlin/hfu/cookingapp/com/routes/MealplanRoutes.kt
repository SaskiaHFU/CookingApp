package hfu.cookingapp.com.routes

import hfu.cookingapp.com.model.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

@kotlinx.serialization.Serializable
data class AddMealToPlanRequest(
    val day: String,
    val bfName: String,
    val luName: String,
    val diName: String,
)

fun Route.addMealToPlanRouting() {
    route("/v1/mealplan") {

        get {
            call.respond(dailymeals)
        }

        put {

            val meal = call.receive<AddMealToPlanRequest>()
            dailyMealsDao.addMealToMealplan(
                day = meal.day,
                bfName = meal.bfName,
                luName = meal.luName,
                diName = meal.diName
            )?.let { _ ->
                call.respond(HttpStatusCode.OK)
            } ?: call.respond(HttpStatusCode.BadRequest)

        }
    }
}

val dailymeals = listOf(
    DailyMeals(
        day = "Monday",
        bfName = "",
        luName = "",
        diName = "",
    ),
    DailyMeals(
        day = "Tuesday",
        bfName = "",
        luName = "",
        diName = "",
    ),
    DailyMeals(
        day = "Wednesday",
        bfName = "",
        luName = "",
        diName = "",
    ),
    DailyMeals(
        day = "Thursday",
        bfName = "",
        luName = "",
        diName = "",
    ),
    DailyMeals(
        day = "Friday",
        bfName = "",
        luName = "",
        diName = "",
    ),
    DailyMeals(
        day = "Saturday",
        bfName = "",
        luName = "",
        diName = "",
    ),
    DailyMeals(
        day = "Sunday",
        bfName = "",
        luName = "",
        diName = "",
    ),
)