package com.acmpo6ou.shop.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.acmpo6ou.shop.R
import com.acmpo6ou.shop.model.Product

@Composable
fun CartScreen(viewModel: ProductsViewModel) {
    if (viewModel.products.size > 0) {
        Column {
            TotalPrice(viewModel)
            ProductList(viewModel) { product: Product ->
                RemoveFromCart(viewModel, product)
            }
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(stringResource(R.string.no_items))
        }
    }
}

@Composable
fun TotalPrice(viewModel: ProductsViewModel) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.padding(8.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            val total = viewModel.products.sumOf { it.price.value.toDouble() }
            val currency = viewModel.products.first().price.currency
            Text(
                stringResource(R.string.total_price, total, currency),
                fontSize = 18.sp,
            )

            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = {}) {
                Text(stringResource(R.string.pay))
            }
        }
    }
}

@Composable
fun RemoveFromCart(viewModel: ProductsViewModel, product: Product) {
    IconButton(onClick = { viewModel.removeFromCart(product) }) {
        Icon(
            painter = painterResource(R.drawable.remove_from_cart),
            tint = MaterialTheme.colors.error,
            contentDescription = stringResource(R.string.remove_from_cart, product.name),
        )
    }
}
