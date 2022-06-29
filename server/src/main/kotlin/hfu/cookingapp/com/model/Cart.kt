package hfu.cookingapp.com.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

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

    suspend fun deleteAllByCartId(id: String): Int
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

    override suspend fun deleteAllByCartId(id: String): Int {
        TODO("Not yet implemented")
    }

    private fun resultRowToCart(row: ResultRow) = CartItem(
        id = row[Cart.id],
        item = row[Cart.item],
    )
}

val cartDao: CartDao = CartDaoImpl()