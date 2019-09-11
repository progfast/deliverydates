package com.streetmart.deliverytimes.viewModel

import androidx.lifecycle.ViewModel
import com.streetmart.deliverytimes.DeliveryTimesApp
import com.streetmart.deliverytimes.repository.ProductRepository
import javax.inject.Inject

class ProductsViewModel : ViewModel() {
    @Inject
    lateinit var productRepository: ProductRepository

    init {
        DeliveryTimesApp.component.inject(this)
    }

    fun loadProducts() = productRepository.fetchProducts()
}
