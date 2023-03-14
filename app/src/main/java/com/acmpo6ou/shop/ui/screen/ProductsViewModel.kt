package com.acmpo6ou.shop.ui.screen

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.acmpo6ou.shop.model.Product
import com.acmpo6ou.shop.model.ProductRepo
import com.acmpo6ou.shop.ui.Screen

class ProductsViewModel : ViewModel() {
    private lateinit var repo: ProductRepo
    lateinit var products: SnapshotStateList<Product>
    lateinit var cartIds: SnapshotStateList<Int>

    fun initialize(repo: ProductRepo) {
        this.repo = repo
        cartIds = repo.getCartIds().toMutableStateList()
        products = repo.getProducts().toMutableStateList()
    }

    fun navigate(screen: Screen) {
        products.clear()
        if (screen == Screen.PRODUCT_LIST) {
            products.addAll(repo.getProducts())
        } else {
            products.addAll(repo.getProducts().filter { it.id in cartIds })
        }
    }

    /**
     * Adds or removes a product from the cart.
     */
    fun toggleCart(product: Product) {
        if (product.id in cartIds) {
            cartIds.remove(product.id)
        } else {
            cartIds.add(product.id)
        }
        repo.saveCartIds(cartIds)
    }

    fun removeFromCart(product: Product) {
        cartIds.remove(product.id)
        products.remove(product)
        repo.saveCartIds(cartIds)
    }
}
