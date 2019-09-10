package com.streetmart.deliverytimes.util

import com.streetmart.deliverytimes.model.Product

interface ItemClickListener {
    fun onItemClick(product: Product)
}