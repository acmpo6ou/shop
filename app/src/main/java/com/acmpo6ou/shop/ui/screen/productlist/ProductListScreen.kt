package com.acmpo6ou.shop.ui.screen.productlist

import androidx.compose.runtime.Composable
import com.acmpo6ou.shop.model.Product
import com.acmpo6ou.shop.ui.screen.ProductList
import com.acmpo6ou.shop.ui.screen.ToggleCart

@Composable
fun ProductListScreen(viewModel: ProductListViewModel) {
    ProductList(viewModel) { product: Product ->
        ToggleCart(viewModel, product)
    }
}
