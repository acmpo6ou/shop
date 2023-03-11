package com.acmpo6ou.shop.ui.screen.productlist

import com.acmpo6ou.shop.model.Product
import com.acmpo6ou.shop.model.ProductRepo
import com.acmpo6ou.shop.ui.screen.ProductsViewModel

class ProductListViewModel : ProductsViewModel() {
    override fun initialize(repo: ProductRepo) {
        super.initialize(repo)
        products.addAll(repo.getProducts())
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
