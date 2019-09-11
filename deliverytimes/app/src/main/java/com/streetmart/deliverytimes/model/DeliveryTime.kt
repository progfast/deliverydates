package com.streetmart.deliverytimes.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DeliveryTime(
    val postalCode: String,
    val deliveryDate: String,
    val isGreenDelivery: Boolean
) : Parcelable