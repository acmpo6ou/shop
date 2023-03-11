package com.acmpo6ou.shop.ui.screen.cart

import com.acmpo6ou.shop.model.Product
import com.acmpo6ou.shop.model.ProductRepo
import com.acmpo6ou.shop.ui.screen.ProductsViewModel

class CartViewModel : ProductsViewModel() {
    override fun initialize(repo: ProductRepo) {
        super.initialize(repo)
        products.addAll(repo.getProducts().take(5))
    }

    override fun onIconClicked(product: Product) {
        TODO("Not yet implemented")
    }
}
