package com.acmpo6ou.shop

import android.content.Context.MODE_PRIVATE
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import com.acmpo6ou.shop.model.ProductRepo.Companion.CART_IDS
import org.junit.After
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @After
    fun clearCartIds() {
        val prefs = context.getSharedPreferences(CART_IDS, MODE_PRIVATE)
        prefs.edit().clear().apply()
    }

    @Test
    fun testShoppingCart() {
        // at the beginning the cart should be empty
        composeTestRule
            .onNodeWithText("0")
            .assertIsDisplayed()

        // add first 3 products to cart (cart badge should be updated correctly)
        composeTestRule
            .onNodeWithContentDescription("Add Henriksdal to cart")
            .performClick()
        composeTestRule
            .onNodeWithText("1")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithContentDescription("Add Lidhult to cart")
            .performClick()
        composeTestRule
            .onNodeWithText("2")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithContentDescription("Add Nyhamn to cart")
            .performClick()
        composeTestRule
            .onNodeWithText("3")
            .assertIsDisplayed()

        // remove Lidhult from cart
        composeTestRule
            .onNodeWithContentDescription("Remove Lidhult from cart")
            .performClick()
        composeTestRule
            .onNodeWithText("2")
            .assertIsDisplayed()

        // go to the cart
        composeTestRule
            .onNodeWithText("Cart")
            .performClick()

        // there should be Henriksdal and Nyhamn, but no Lidhult
        composeTestRule
            .onNodeWithText("Henriksdal")
            .assertExists()
        composeTestRule
            .onNodeWithText("Nyhamn")
            .assertExists()
        composeTestRule
            .onNodeWithText("Lidhult")
            .assertDoesNotExist()

        // the total price should be correct
        composeTestRule
            .onNodeWithText("Total: 3894.00 kr")
            .assertExists()

        // remove Nyhamn from the cart
        composeTestRule
            .onNodeWithContentDescription("Remove Nyhamn from cart")
            .performClick()

        // it shouldn't be in the cart anymore
        composeTestRule
            .onNodeWithContentDescription("Remove Nyhamn from cart")
            .assertIsNotDisplayed()

        // the total price and cart badge should be updated
        composeTestRule
            .onNodeWithText("Total: 499.00 kr")
            .assertExists()
        composeTestRule
            .onNodeWithText("1")
            .assertIsDisplayed()

        // go back to product list
        composeTestRule
            .onNodeWithText("Search")
            .performClick()

        // only Henriksdal should be in the cart, but not Lidhult and Nyhamn
        composeTestRule
            .onNodeWithContentDescription("Remove Henriksdal from cart")
            .assertExists()
        composeTestRule
            .onNodeWithContentDescription("Add Lidhult to cart")
            .assertExists()
        composeTestRule
            .onNodeWithContentDescription("Add Nyhamn to cart")
            .assertExists()
    }
}
