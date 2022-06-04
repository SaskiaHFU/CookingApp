package hfu.cookingapp.com.model

import hfu.cookingapp.com.model.DatabaseFactory.dbQuery
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

@Serializable
data class RecipeCategory(
    val id: String,
    val name: String,
    val recipes: String,
)

object RecipeCategories : Table() {
    val id = varchar("id", 128)
    val name = varchar("name", 128)
    val recipes = varchar("recipes", 128)
    override val primaryKey = PrimaryKey(id)
}

interface RecipeCatItemDao {
    suspend fun itemsByCatId(id: String): List<RecipeCategory>
    suspend fun addRecipeCatItem(
        id: String,
        name: String,
        recipes: String,
    ): RecipeCategory?

    suspend fun deleteAllByCartId(id: String): Int
}

class RecipeCatItemDaoImpl : RecipeCatItemDao {

    override suspend fun itemsByCatId(id: String): List<RecipeCategory> = DatabaseFactory.dbQuery {
        RecipeCategories.select {
            RecipeCategories.id.eq(id)
        }.map(::resultRowToRecipeCat)
    }

    override suspend fun addRecipeCatItem(id: String, name: String, recipes: String): RecipeCategory? =
        dbQuery {
            val insertStatement = RecipeCategories.insert {
                it[RecipeCategories.id] = id
                it[RecipeCategories.name] = name
                it[RecipeCategories.recipes] = recipes
            }
            insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToRecipeCat)
        }

    override suspend fun deleteAllByCartId(id: String): Int {
        TODO("Not yet implemented")
    }

    private fun resultRowToRecipeCat(row: ResultRow) = RecipeCategory(
        id = row[RecipeCategories.id],
        name = row[RecipeCategories.name],
        recipes = row[RecipeCategories.recipes],
    )

}

val recipeCatDao: RecipeCatItemDao = RecipeCatItemDaoImpl()