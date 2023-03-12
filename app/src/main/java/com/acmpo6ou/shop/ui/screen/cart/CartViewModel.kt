package com.acmpo6ou.shop.ui.screen.cart

import com.acmpo6ou.shop.model.Product
import com.acmpo6ou.shop.ui.screen.ProductsViewModel

class CartViewModel : ProductsViewModel() {
    override fun initialize() {
        super.initialize()
        val cartIds = repo.getCartIds()
        products.addAll(repo.getProducts().filter { it.id in cartIds })
    }

    override fun onIconClicked(product: Product) {
        products.remove(product)
        val cartIds = products.map { it.id }
        repo.saveCartIds(cartIds)
    }
}
