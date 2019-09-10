package com.streetmart.deliverytimes.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.streetmart.deliverytimes.DeliveryTimesApp
import com.streetmart.deliverytimes.api.ApiResponse
import com.streetmart.deliverytimes.model.Product
import com.streetmart.deliverytimes.api.WebService
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class ProductRepository {

    @Inject
    lateinit var webService: WebService

    init {
        DeliveryTimesApp.component.inject(this)
    }

    fun fetchProducts(): MutableLiveData<ApiResponse> {
        val apiResponse = MutableLiveData<ApiResponse>()
        val call = webService.fetchProducts()
        call.enqueue(object : retrofit2.Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if (response.isSuccessful) {
                    apiResponse.value = ApiResponse.create(response.body())
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                apiResponse.value = ApiResponse.create(t)
            }

        })
        return apiResponse
    }
}