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
    val id: String,
    val name: String,
    val price: Price,
    val info: Info,
    val type: String,
    val imageUrl: String,
) {
    @Serializable
    data class Price(
        val value: Double,
        val currency: String,
    )

    @Serializable
    data class Info(
        val color: String,
        val material: String? = null,
        val numberOfSeats: Int? = null,
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
