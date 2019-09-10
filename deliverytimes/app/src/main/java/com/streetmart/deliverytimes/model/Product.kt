package com.streetmart.deliverytimes.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val productId: String,
    val name: String,
    val deliveryDays: List<String>,
    val productType: String,
    val daysInAdvance: Int
) : Parcelable