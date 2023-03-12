package com.acmpo6ou.shop.ui.screen.productlist

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import com.acmpo6ou.shop.R
import com.acmpo6ou.shop.model.Product
import com.acmpo6ou.shop.ui.screen.ProductList

@Composable
fun ProductListScreen(viewModel: ProductListViewModel) {
    ProductList(viewModel) { product: Product ->
        ToggleCart(viewModel, product)
    }
}

@Composable
fun ToggleCart(viewModel: ProductListViewModel, product: Product) {
    IconButton(onClick = { viewModel.onIconClicked(product) }) {
        val cartIds = remember { viewModel.cartIds }
        Icon(
            painter = painterResource(id = R.drawable.shopping_cart),
            tint = if (product.id in cartIds) {
                MaterialTheme.colors.primary
            } else {
                MaterialTheme.colors.onBackground
            },
            // TODO: should be dynamic depending on add/remove
            contentDescription = "Add ${product.name} to cart",
        )
    }
}
