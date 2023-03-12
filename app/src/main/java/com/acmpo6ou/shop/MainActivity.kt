package com.acmpo6ou.shop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.acmpo6ou.shop.model.ProductRepo
import com.acmpo6ou.shop.model.ProductRepo.Companion.CART_IDS
import com.acmpo6ou.shop.ui.screen.MainContent
import com.acmpo6ou.shop.ui.screen.cart.CartViewModel
import com.acmpo6ou.shop.ui.screen.productlist.ProductListViewModel
import com.acmpo6ou.shop.ui.theme.ShopTheme

class MainActivity : ComponentActivity() {
    private val productListViewModel: ProductListViewModel by viewModels()
    private val cartViewModel: CartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repo = ProductRepo(
            assets,
            getSharedPreferences(CART_IDS, MODE_PRIVATE),
        )

        productListViewModel.repo = repo
        cartViewModel.repo = repo
        productListViewModel.initialize()
        cartViewModel.initialize()

        setContent {
            ShopTheme {
                MainContent(productListViewModel, cartViewModel)
            }
        }
    }
}
