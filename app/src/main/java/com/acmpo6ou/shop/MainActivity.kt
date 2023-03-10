package com.acmpo6ou.shop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.acmpo6ou.shop.model.Product
import com.acmpo6ou.shop.model.ProductRepo
import com.acmpo6ou.shop.ui.ProductsViewModel
import com.acmpo6ou.shop.ui.theme.ShopTheme

class MainActivity : ComponentActivity() {
    private val productsViewModel: ProductsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repo = ProductRepo(assets)
        productsViewModel.initialize(repo)

        setContent {
            ShopTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    ProductItemPreview()
                }
            }
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Card(
        modifier = Modifier
            .border(BorderStroke(1.dp, Color.Black))
            .padding(8.dp)
            .wrapContentHeight(Alignment.Top)
            .clickable {}
            .fillMaxWidth(),
        elevation = 10.dp,
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
                info = mapOf(
                    "material" to "wood with cover",
                    "color" to "white",
                ),
                type = "chair",
                imageUrl = "https://shop.static.ingka.ikea.com/PIAimages/0462849_PE608354_S4.JPG",
            ),
        )
    }
}
