package hfu.cookingapp.com.model

import hfu.cookingapp.com.model.DatabaseFactory.dbQuery
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*

@Serializable
data class CartItem(
    val id: String,
    val item: String,
)

object Cart : Table() {
    val id = varchar("id", 128)
    val item = varchar("item", 128)
    override val primaryKey = PrimaryKey(id)
}

interface CartDao {
    suspend fun itemsByCartId(id: String): List<CartItem>
    suspend fun addCartItem(
        id: String,
        item: String,
    ): CartItem?

    suspend fun deleteAll(): Unit
    suspend fun deleteAllByCartId(id: String): Unit
}

class CartDaoImpl : CartDao {
    override suspend fun itemsByCartId(id: String): List<CartItem> = DatabaseFactory.dbQuery {
        Cart.select {
            Cart.id.eq(id)
        }.map(::resultRowToCart)
    }
    override suspend fun addCartItem(id: String, item: String): CartItem? =
        DatabaseFactory.dbQuery {
            val insertStatement = Cart.insert {
                it[Cart.id] = id
                it[Cart.item] = item
            }
            insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToCart)
        }

    override suspend fun deleteAll(): Unit {
        Cart.deleteAll()
    }

    override suspend fun deleteAllByCartId(id: String): Unit = dbQuery {
        Cart.deleteWhere {
            Cart.id.eq(id)
        }
    }

    private fun resultRowToCart(row: ResultRow) = CartItem(
        id = row[Cart.id],
        item = row[Cart.item],
    )
}

val cartDao: CartDao = CartDaoImpl()