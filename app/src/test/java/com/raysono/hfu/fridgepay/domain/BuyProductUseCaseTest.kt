package com.raysono.hfu.fridgepay.domain

import com.saskiahfu.hfu.cookingapp.data.ProductsRepository
import com.saskiahfu.hfu.cookingapp.data.ShoppingCartRepository
import com.saskiahfu.hfu.cookingapp.data.UserSettingsRepository
import com.saskiahfu.hfu.cookingapp.data.network.WebService
import com.saskiahfu.hfu.cookingapp.domain.BuyProductUseCase
import com.saskiahfu.hfu.cookingapp.domain.model.BuyableProduct
import com.saskiahfu.hfu.cookingapp.domain.model.Product
import com.saskiahfu.hfu.cookingapp.domain.model.ProductIcon
import com.saskiahfu.hfu.cookingapp.domain.model.ProductId
import com.saskiahfu.hfu.cookingapp.domain.model.ShoppingCart
import com.saskiahfu.hfu.cookingapp.domain.model.ShoppingCartId
import io.kotest.matchers.booleans.shouldBeFalse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class BuyProductUseCaseTest {

    @MockK
    private lateinit var mockWebService: WebService
    @MockK
    private lateinit var mockUserSettingsRepository: UserSettingsRepository
    @MockK
    private lateinit var mockProductsRepository: ProductsRepository
    @MockK
    private lateinit var mockShoppingCartRepository: ShoppingCartRepository

    private lateinit var useCase: BuyProductUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        useCase = BuyProductUseCase(
            mockWebService,
            mockUserSettingsRepository,
            mockProductsRepository,
            mockShoppingCartRepository,
        )
    }

    @Test
    fun `given product does not exist, when invoked, false should be returned`() = runTest {
        // given
        coEvery { mockProductsRepository.getBuyableProductById(ProductId(any())) } returns null

        // when
        val result = useCase(ProductId("1234"))

        // then
        result.shouldBeFalse()
    }

    @Test
    fun `given an existing product, when invoked, it should be added to the cart`() = runTest {
        // given
        val expectedProductId = ProductId("1234")
        coEvery { mockShoppingCartRepository.getCurrentCart() } returns ShoppingCart.Empty(
            ShoppingCartId("")
        )
        coEvery { mockProductsRepository.getBuyableProductById(ProductId(any())) } returns buyableProduct(expectedProductId)

        // when
        useCase(expectedProductId)

        // then
        coVerify {
            mockShoppingCartRepository.updateCart(
                match { cart ->
                    (cart as ShoppingCart.Unpayed).products.any { (product, count) ->
                        product.product.id == expectedProductId && count == 1
                    }
                }
            )
        }
    }

    private fun buyableProduct(id: ProductId = ProductId("123")) = BuyableProduct(
        Product.create(
            id,
            "name",
            ProductIcon.Unknown,
            "description",
        )!!,
        13.13,
    )
}
