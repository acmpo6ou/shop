package com.acmpo6ou.shop

import android.content.Context.MODE_PRIVATE
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
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
        // add first 3 products to cart
        composeTestRule
            .onNodeWithContentDescription("Add Henriksdal to cart")
            .performClick()
        composeTestRule
            .onNodeWithContentDescription("Add Lidhult to cart")
            .performClick()
        composeTestRule
            .onNodeWithContentDescription("Add Nyhamn to cart")
            .performClick()

        // remove Lidhult from cart
        composeTestRule
            .onNodeWithContentDescription("Remove Lidhult from cart")
            .performClick()

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

        // the total price should be updated
        composeTestRule
            .onNodeWithText("Total: 499.00 kr")
            .assertExists()

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
