package com.streetmart.deliverytimes.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.streetmart.deliverytimes.model.DeliveryTime
import com.streetmart.deliverytimes.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TimeSlotsViewModel : ViewModel() {

    //this model is calculating data locally, so no repository is needed,
    // in future when data needed to be calculated bt backend, then repos would be added here so UI remains intact
    fun fetchDeliveryTimes(
        postalCode: String,
        products: List<Product>
    ): MutableLiveData<List<DeliveryTime>> {
        val deliveryTimes = MutableLiveData<List<DeliveryTime>>()

        GlobalScope.launch(Dispatchers.Main) {
            delay(200)
            deliveryTimes.value = listOf(
                DeliveryTime("195 51", "2012-09-25", false),
                DeliveryTime("195 51", "2012-09-25", true)
            )
        }

        return deliveryTimes

    }
}
