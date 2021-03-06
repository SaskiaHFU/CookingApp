package hfu.cookingapp.com.model

import hfu.cookingapp.com.model.DatabaseFactory.dbQuery
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.*

@Serializable
data class RecipeItem(
    val id: String,
    val name: String,
    val img: String,
    val ingredients: String,
    val steps: String,
    val category: String,
    val sourceName: String,
    val sourceUri: String
)

object RecipeItems : Table() {
    val id = varchar("id", 128)
    val name = varchar("name", 1024)

    val img = varchar("img", 1024)
    val ingredients = varchar("ingredients", 1024)
    val steps = varchar("steps", 1024)
    val category = varchar("category", 1024)
    val sourceName = varchar("sourceName", 1024)
    val sourceUri = varchar("sourceUri", 1024)
    override val primaryKey = PrimaryKey(id)
}

interface RecipeDao {
    suspend fun itemsByRecipeId(id: String): List<RecipeItem>
    suspend fun addRecipeItem(
        id: String,
        name: String,
        img: String,
        ingredients: String,
        steps: String,
        category: String,
        sourceName: String,
        sourceUri: String
    ): RecipeItem?

}

//var jsonIngr = "";
//var jsonSteps = "";


class RecipeDaoImpl : RecipeDao {
    override suspend fun itemsByRecipeId(id: String): List<RecipeItem> = dbQuery {
        RecipeItems.select {
            RecipeItems.id.eq(id)
        }.map(::resultRowToRecipeItem)
    }

    override suspend fun addRecipeItem(
        id: String,
        name: String,
        img: String,
        ingredients: String,
        steps: String,
        category: String,
        sourceName: String,
        sourceUri: String
    ): RecipeItem? =
        dbQuery {

            val insertStatement = RecipeItems.insert {

                it[RecipeItems.id] = id
                it[RecipeItems.name] = name
                it[RecipeItems.img] = img
                it[RecipeItems.ingredients] = ingredients
                it[RecipeItems.steps] = steps
                it[RecipeItems.category] = category
                it[RecipeItems.sourceName] = sourceName
                it[RecipeItems.sourceUri] = sourceUri
            }
            insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToRecipeItem)

        }

//Aus db raus
    private fun resultRowToRecipeItem(row: ResultRow) = RecipeItem(
        id = row[RecipeItems.id],
        name = row[RecipeItems.name],
        img = row[RecipeItems.img],
        ingredients = row[RecipeItems.ingredients],
        steps = row[RecipeItems.steps],
        category = row[RecipeItems.category],
        sourceName = row[RecipeItems.sourceName],
        sourceUri = row[RecipeItems.sourceUri],
    )
}

val recipeDao: RecipeDao = RecipeDaoImpl()
