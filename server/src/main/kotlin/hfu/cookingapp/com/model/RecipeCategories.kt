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
)

object RecipeCategories : Table() {
    val id = varchar("id", 128)
    val name = varchar("name", 128)
    override val primaryKey = PrimaryKey(id)
}

interface RecipeCatDao {
    suspend fun itemsByCatId(id: String): List<RecipeCategory>
    suspend fun addRecipeCatItem(
        id: String,
        name: String,
    ): RecipeCategory?

    suspend fun deleteAllByCatId(id: String): Int
}

class RecipeCatDaoImpl : RecipeCatDao {

    override suspend fun itemsByCatId(id: String): List<RecipeCategory> = DatabaseFactory.dbQuery {
        RecipeCategories.select {
            RecipeCategories.id.eq(id)
        }.map(::resultRowToRecipeCat)
    }

    override suspend fun addRecipeCatItem(id: String, name: String): RecipeCategory? =
        dbQuery {
            val insertStatement = RecipeCategories.insert {
                it[RecipeCategories.id] = id
                it[RecipeCategories.name] = name
            }
            insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToRecipeCat)
        }

    override suspend fun deleteAllByCatId(id: String): Int {
        TODO("Not yet implemented")
    }

    private fun resultRowToRecipeCat(row: ResultRow) = RecipeCategory(
        id = row[RecipeCategories.id],
        name = row[RecipeCategories.name],
    )

}

val recipeCatDao: RecipeCatDao = RecipeCatDaoImpl()