package com.streetmart.deliverytimes.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.streetmart.deliverytimes.DeliveryTimesApp
import com.streetmart.deliverytimes.api.ApiResponse
import com.streetmart.deliverytimes.repository.ProductRepository
import javax.inject.Inject

class ProductsViewModel : ViewModel() {
    @Inject
    lateinit var productRepository: ProductRepository
    private var apiResult: MutableLiveData<ApiResponse>

    init {
        DeliveryTimesApp.component.inject(this)

        apiResult = productRepository.fetchProducts()
    }

    fun loadProducts() = apiResult
}
