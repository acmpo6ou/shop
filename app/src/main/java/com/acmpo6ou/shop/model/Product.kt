package com.acmpo6ou.shop.model
import android.content.res.AssetManager
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Serializable
data class Products(
    val products: List<Product>,
)

@Serializable
data class Product(
    val id: Int,
    val name: String,
    val price: Price,
    val info: Map<String, String>,
    val type: String,
    val imageUrl: String,
) {
    val prettyPrice get() = "${price.value} ${price.currency}"
    val prettyInfo: String
        get() {
            var result = ""
            info.forEach { (key, value) ->
                result += "${key.capitalize()}: $value\n"
            }
            return result
        }

    @Serializable
    data class Price(
        val value: Float,
        val currency: String,
    )
}

class ProductRepo(private val assets: AssetManager) {
    /**
     * Loads the products from assets.
     */
    fun getProducts(): List<Product> {
        val json = assets.open("products.json")
            .bufferedReader()
            .use { it.readText() }
        return Json.decodeFromString<Products>(json).products
    }
}
