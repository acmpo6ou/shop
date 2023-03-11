package com.acmpo6ou.shop.ui.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.acmpo6ou.shop.model.Product
import com.acmpo6ou.shop.model.ProductRepo

abstract class ProductsViewModel : ViewModel() {
    lateinit var repo: ProductRepo
    val products = mutableStateListOf<Product>()
    val cartIds = mutableStateListOf<Int>()

    open fun initialize(repo: ProductRepo) {
        this.repo = repo
        cartIds.addAll(repo.getCartIds())
    }

    abstract fun onIconClicked(product: Product)
}