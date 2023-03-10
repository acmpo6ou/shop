package com.acmpo6ou.shop.model
import android.content.SharedPreferences
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
    val info: Info,
    val type: String,
    val imageUrl: String,
) {
    val prettyPrice get() = "${"%.2f".format(price.value)} ${price.currency}"
    val prettyInfo: String
        get() {
            return if (type == "chair") {
                "Color: ${info.color}\nMaterial: ${info.material}"
            } else {
                "Color: ${info.color}\nNumber of seats: ${info.numberOfSeats}"
            }
        }

    @Serializable
    data class Price(
        val value: Float,
        val currency: String,
    )

    @Serializable
    data class Info(
        val color: String,
        val material: String? = null,
        val numberOfSeats: Int? = null,
    )
}

class ProductRepo(
    private val assets: AssetManager,
    private val prefs: SharedPreferences,
) {
    /**
     * Loads the products from assets.
     */
    fun getProducts(): List<Product> {
        val json = assets.open("products.json")
            .bufferedReader()
            .use { it.readText() }
        return Json.decodeFromString<Products>(json).products
    }

    fun getCartIds(): List<Int> {
        return prefs.getStringSet(CART_IDS, setOf())
            ?.map { it.toInt() } ?: listOf()
    }

    fun saveCartIds(ids: List<Int>) {
        prefs.edit()
            .putStringSet(CART_IDS, ids.map { it.toString() }.toSet())
            .apply()
    }

    companion object {
        const val CART_IDS = "cart_ids"
    }
}
