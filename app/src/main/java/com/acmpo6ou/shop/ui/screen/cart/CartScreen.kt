package com.acmpo6ou.shop.ui.screen.cart

import androidx.compose.runtime.Composable
import com.acmpo6ou.shop.ui.screen.ProductList

@Composable
fun CartScreen(viewModel: CartViewModel) {
    ProductList(viewModel)
}
