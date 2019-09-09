package com.streetmart.deliverytimes.model

class Product(
    val productId: String,
    val name: String,
    val deliveryDays: List<String>,
    val productType: String,
    val daysInAdvance: Int
)