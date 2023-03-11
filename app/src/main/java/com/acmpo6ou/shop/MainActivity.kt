package com.acmpo6ou.shop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.acmpo6ou.shop.model.ProductRepo
import com.acmpo6ou.shop.model.ProductRepo.Companion.CART_IDS
import com.acmpo6ou.shop.ui.screen.cart.CartScreen
import com.acmpo6ou.shop.ui.screen.productlist.ProductListScreen
import com.acmpo6ou.shop.ui.screen.productlist.ProductsViewModel
import com.acmpo6ou.shop.ui.theme.ShopTheme

class MainActivity : ComponentActivity() {
    private val productsViewModel: ProductsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repo = ProductRepo(
            assets,
            getSharedPreferences(CART_IDS, MODE_PRIVATE),
        )
        productsViewModel.initialize(repo)

        setContent {
            ShopTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    Column {
                        NavHost(
                            navController = rememberNavController(),
                            startDestination = "product_list",
                        ) {
                            composable("product_list") { ProductListScreen(productsViewModel) }
                            composable("cart") { CartScreen() }
                        }
                    }
                }
            }
        }
    }
}
