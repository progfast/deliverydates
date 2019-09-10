package com.streetmart.deliverytimes.api

import com.streetmart.deliverytimes.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface WebService {
@GET("products.json")
fun fetchProducts(): Call<List<Product>>
}