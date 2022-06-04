package hfu.cookingapp.com.model

import hfu.cookingapp.com.model.DatabaseFactory.dbQuery
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.*

@Serializable
data class RecipeItem(
    val recipeId: String,
    val name: String,
//    val img: String,
//    val ingredients: List<String>,
//    val steps: List<String>,
//    val category: String,
    val sourceName: String,
    val sourceUri: String
)

object RecipeItems : Table() {
    val recipeId = varchar("id", 128)
    val name = varchar("name", 1024)
//    val img = varchar("img", 1024)
//    val ingredients = varchar("ingredients", 1024)
//    val steps = varchar("steps", 1024)
//    val category = varchar("category", 1024)
    val sourceName = varchar("sourceName", 1024)
    val sourceUri = varchar ("sourceUri", 1024)
    override val primaryKey = PrimaryKey(recipeId)
}

interface RecipeDao {
    suspend fun itemsByRecipeId(id: String): List<RecipeItem>
    suspend fun addRecipeItem(
        recipeId: String,
        name: String,
//        img: String,
//        ingredients: List<String>,
//        steps: List<String>,
//        category: String,
        sourceName: String,
        sourceUri: String

    ): RecipeItem?

}
var jsonIngr = "";
var jsonSteps = "";


class RecipeDaoImpl : RecipeDao {
    override suspend fun itemsByRecipeId(id: String): List<RecipeItem> = dbQuery {
        RecipeItems.select {
            RecipeItems.recipeId.eq(id)
        }.map(::resultRowToRecipeItem)
    }

    override suspend fun addRecipeItem(
        id: String,
        name: String,
//        img: String,
//        ingredients: List<String>,
//        steps: List<String>,
//        category: String,
        sourceName: String,
        sourceUri: String
    ): RecipeItem? =
        dbQuery {

//            val jsonIngr = Json.encodeToString(ListSerializer(String.serializer()), ingredients);
//            val jsonSteps = Json.encodeToString(ListSerializer(String.serializer()), steps);

            val insertStatement = RecipeItems.insert {

                it[recipeId] = id
                it[RecipeItems.name] = name
//                it[RecipeItems.img] = img
//                it[RecipeItems.ingredients] = jsonIngr
//                it[RecipeItems.steps] = jsonSteps
//                it[RecipeItems.category] = category
            }
            insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToRecipeItem)

        }

//Aus db raus

    private fun resultRowToRecipeItem(row: ResultRow) = RecipeItem(
        recipeId = row[RecipeItems.recipeId],
        name = row[RecipeItems.name],
//        img = row[RecipeItems.img],
//        ingredients = Json.decodeFromString(ListSerializer(String.serializer()), jsonIngr), //decode,
//        steps = Json.decodeFromString(ListSerializer(String.serializer()), jsonIngr),
//        category = row[RecipeItems.category],
        sourceName = row[RecipeItems.sourceName],
        sourceUri = row[RecipeItems.sourceUri],
    )
}

val recipeDao: RecipeDao = RecipeDaoImpl()
