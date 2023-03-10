package com.acmpo6ou.shop.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.acmpo6ou.shop.R
import com.acmpo6ou.shop.model.Product
import com.acmpo6ou.shop.ui.theme.ShopTheme

@Composable
fun ProductList(viewModel: ProductsViewModel) {
    val products = remember { viewModel.products }
    LazyColumn {
        items(items = products) {
            ProductItem(it)
        }
    }
}

@Composable
fun ProductItem(product: Product) {
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
                contentDescription = "${product.name} image",
                modifier = Modifier.size(100.dp),
            )
            Column {
                Row {
                    Text(
                        text = product.name,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painterResource(id = R.drawable.add_shopping_cart),
                            "Add ${product.name} to cart",
                        )
                    }
                }
                Text(text = product.prettyInfo)
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

@Preview(showBackground = true)
@Composable
fun ProductItemPreview() {
    ShopTheme {
        ProductItem(
            Product(
                id = 1,
                name = "Henriksdal",
                price = Product.Price(499f, "kr"),
                info = Product.Info(
                    color = "white",
                    material = "wood with cover",
                ),
                type = "chair",
                imageUrl = "https://shop.static.ingka.ikea.com/PIAimages/0462849_PE608354_S4.JPG",
            ),
        )
    }
}
