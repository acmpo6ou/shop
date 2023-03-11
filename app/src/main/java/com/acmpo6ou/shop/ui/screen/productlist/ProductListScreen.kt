package com.acmpo6ou.shop.ui.screen.productlist

import androidx.compose.runtime.Composable
import com.acmpo6ou.shop.ui.screen.ProductList
import com.acmpo6ou.shop.ui.screen.ProductsViewModel

@Composable
fun ProductListScreen(viewModel: ProductsViewModel) {
    ProductList(viewModel)
}
