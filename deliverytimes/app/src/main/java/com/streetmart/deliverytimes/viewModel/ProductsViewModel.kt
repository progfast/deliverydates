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
        private val apiResult: MutableLiveData<ApiResponse> by lazy { MutableLiveData<ApiResponse>() }

    init {
        DeliveryTimesApp.component.inject(this)

        val apiResponse = productRepository.fetchProducts()
        apiResult.value = apiResponse.value
    }

    fun loadProducts() = apiResult
}
