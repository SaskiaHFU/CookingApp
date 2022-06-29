package hfu.cookingapp.com.model

import hfu.cookingapp.com.model.DatabaseFactory.dbQuery
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*

@Serializable
data class DailyMeals(
    val day: String,
    val bfName: String,
    val luName: String,
    val diName: String,
)

object Mealplan : Table() {
    val day = varchar("day", 1024)
    val bfName = varchar("bfName", 1024)
    val luName = varchar("luName", 1024)
    val diName = varchar("diName", 1024)
    override val primaryKey = PrimaryKey(day)
}

interface DailyMealsDao {
    suspend fun mealsByDay(day: String): List<DailyMeals>
    suspend fun addMealToMealplan(
        day: String,
        bfName: String,
        luName: String,
        diName: String,
    ): DailyMeals?

//    suspend fun deleteAllByDay(day: String): String
}

class DailyMealsDaoImpl : DailyMealsDao {
    override suspend fun mealsByDay(day: String): List<DailyMeals> = dbQuery {
        Mealplan.select {
            Mealplan.day.eq(day)
        }.map(::resultRowToDailyMeals)
    }

    override suspend fun addMealToMealplan(day: String, bfName: String, luName: String, diName: String): DailyMeals? =
        dbQuery {
            val insertStatement = Mealplan.insert {
                it[Mealplan.day] = day
                it[Mealplan.bfName] = bfName
                it[Mealplan.luName] = luName
                it[Mealplan.diName] = diName
            }
            insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToDailyMeals)

        }


    private fun resultRowToDailyMeals(row: ResultRow) = DailyMeals(
        day = row[Mealplan.day],
        bfName = row[Mealplan.bfName],
        luName = row[Mealplan.luName],
        diName = row[Mealplan.diName],
    )
}

val dailyMealsDao: DailyMealsDao = DailyMealsDaoImpl()