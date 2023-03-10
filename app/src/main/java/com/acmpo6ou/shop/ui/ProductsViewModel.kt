package com.acmpo6ou.shop.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.acmpo6ou.shop.model.Product
import com.acmpo6ou.shop.model.ProductRepo

class ProductsViewModel : ViewModel() {
    lateinit var repo: ProductRepo
    val products = mutableStateListOf<Product>()
    val cartIds = MutableLiveData<List<Int>>()

    fun initialize(repo: ProductRepo) {
        this.repo = repo
        products.addAll(repo.getProducts())
        // TODO: load cart ids
    }
}
