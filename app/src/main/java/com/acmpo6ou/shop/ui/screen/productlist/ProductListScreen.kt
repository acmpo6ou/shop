package com.acmpo6ou.shop.ui.screen.productlist

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
    IconButton(onClick = { viewModel.toggleCart(product) }) {
        val cartIds = remember { viewModel.cartIds }
        val tint: Color
        val description: String

        if (product.id in cartIds) {
            tint = MaterialTheme.colors.primary
            description = stringResource(R.string.add_to_cart, product.name)
        } else {
            tint = MaterialTheme.colors.onBackground
            description = stringResource(R.string.remove_from_cart, product.name)
        }

        Icon(
            painter = painterResource(id = R.drawable.shopping_cart),
            tint = tint,
            contentDescription = description,
        )
    }
}
