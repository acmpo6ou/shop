package com.acmpo6ou.shop.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.acmpo6ou.shop.model.Product
import com.acmpo6ou.shop.model.ProductRepo

class ProductsViewModel : ViewModel() {
    lateinit var repo: ProductRepo
    val products = mutableStateListOf<Product>()
    val cartIds = mutableStateListOf<Int>()

    fun initialize(repo: ProductRepo) {
        this.repo = repo
        products.addAll(repo.getProducts())
        // TODO: load cart ids
    }

    fun onCartClicked(product: Product) {
        if (product.id in cartIds) {
            cartIds.remove(product.id)
        } else {
            cartIds.add(product.id)
        }
        // TODO: save cart ids
    }
}
