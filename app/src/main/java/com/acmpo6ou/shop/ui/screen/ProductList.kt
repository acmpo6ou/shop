package com.acmpo6ou.shop.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.acmpo6ou.shop.model.Product

@Composable
fun ProductList(
    viewModel: ProductsViewModel,
    icon: @Composable (Product) -> Unit,
) {
    val products = remember { viewModel.products }
    LazyColumn {
        items(items = products) {
            ProductItem(it, icon)
        }
    }
}

@Composable
fun ProductItem(
    product: Product,
    icon: @Composable (Product) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .wrapContentHeight(Alignment.Top)
            .clickable {}
            .fillMaxWidth(),
        elevation = 5.dp,
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier.size(100.dp),
            )
            Column {
                Row {
                    Text(
                        text = product.name,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    icon(product)
                }
                Text(text = product.getPrettyInfo(LocalContext.current))
                Text(
                    text = product.prettyPrice,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}
