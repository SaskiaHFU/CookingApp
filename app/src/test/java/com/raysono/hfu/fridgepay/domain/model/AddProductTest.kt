package com.raysono.hfu.fridgepay.domain.model

import com.saskiahfu.hfu.cookingapp.domain.model.*
import io.kotest.matchers.maps.shouldContainKey
import io.kotest.matchers.shouldBe
import org.junit.Test

class AddProductTest {

    @Test
    fun `given empty cart, when adding a product, then the new cart should contain it`() {
        // given
        val cart = ShoppingCart.Empty(ShoppingCartId("123"))
        val product = BuyableProduct(
            Product.create(
                ProductId("123"),
                "name",
                ProductIcon.Unknown,
                "description",
            )!!,
            42.0,
        )

        // when
        val newCart = addProduct(cart, product)

        // then
        (newCart as ShoppingCart.Unpayed).products.shouldContainKey(product)
    }

    @Test
    fun `given empty cart, when adding product, new cart should contain it once`() {
        // given
        val cart = ShoppingCart.Empty(ShoppingCartId("123"))
        val product = BuyableProduct(
            Product.create(ProductId("abc"), "name", ProductIcon.Unknown, "")!!,
            0.0,
        )

        // when
        val newCart = addProduct(cart, product)

        // then
        (newCart as ShoppingCart.Unpayed).products[product] shouldBe 1
    }

    @Test
    fun `given cart contains product already, when adding product, new cart should contain it once more`() {
        // given
        val product = BuyableProduct(
            Product.create(ProductId("abc"), "name", ProductIcon.Unknown, "")!!,
            0.0,
        )
        val cart = ShoppingCart.Unpayed.create(
            ShoppingCartId("123"),
            mapOf(product to 1),
            0.0,
        )

        // when
        val newCart = addProduct(cart, product)

        // then
        (newCart as ShoppingCart.Unpayed).products[product] shouldBe 2
    }

    @Test
    fun `given cart contains product already, when adding product, total cost should be increased by product price`() {
        // given
        val price = 42.13
        val product = BuyableProduct(
            Product.create(ProductId("abc"), "name", ProductIcon.Unknown, "")!!,
            price,
        )
        val cart = ShoppingCart.Unpayed.create(
            ShoppingCartId("123"),
            mapOf(product to 1),
            price,
        )

        // when
        val newCart = addProduct(cart, product)

        // then
        (newCart as ShoppingCart.Unpayed).totalCost shouldBe price * 2
    }
}
