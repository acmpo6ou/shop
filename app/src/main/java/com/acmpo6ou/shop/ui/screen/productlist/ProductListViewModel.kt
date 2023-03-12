package com.acmpo6ou.shop.ui.screen.productlist

import androidx.compose.runtime.mutableStateListOf
import com.acmpo6ou.shop.model.Product
import com.acmpo6ou.shop.ui.screen.ProductsViewModel

class ProductListViewModel : ProductsViewModel() {
    val cartIds = mutableStateListOf<Int>()

    override fun initialize() {
        super.initialize()
        cartIds.clear()
        products.addAll(repo.getProducts())
        cartIds.addAll(repo.getCartIds())
    }

    override fun onIconClicked(product: Product) {
        if (product.id in cartIds) {
            cartIds.remove(product.id)
        } else {
            cartIds.add(product.id)
        }
        repo.saveCartIds(cartIds)
    }
}
