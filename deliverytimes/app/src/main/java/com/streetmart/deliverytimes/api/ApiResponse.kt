package com.streetmart.deliverytimes.api

import com.streetmart.deliverytimes.model.Product

class ApiResponse(val products: List<Product>?, val errorMessage: String?) {

    companion object {
        fun create(error: Throwable):ApiResponse {
            return ApiResponse(null, error.message)
        }

        fun create(products: List<Product>?):ApiResponse {
            return ApiResponse(products, null)
        }
    }
}
