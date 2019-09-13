package com.streetmart.deliverytimes.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.streetmart.deliverytimes.model.DeliveryTime
import com.streetmart.deliverytimes.model.Product
import com.streetmart.deliverytimes.util.ProductType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random


fun Date.onOrBefore(futureDate: Date): Boolean {
    return this.before(futureDate) || this == futureDate
}

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
            val validDeliveryDates =
                mutableListOf<Date>()
            products.forEach { product ->
                validDeliveryDates.addAll(findValidDates(product))
            }

            validDeliveryDates.sortBy { it }

            val deliveryObjects = mutableListOf<DeliveryTime>()
            val formatter = SimpleDateFormat("dd MMM yy HH:mm")

            validDeliveryDates.forEach { date ->
                val output = formatter.format(date)
                val isGreen = isGreenDelivery(date)
                deliveryObjects.add(DeliveryTime(postalCode, output, isGreen))
            }

            val greenDeliveries = deliveryObjects.filter { it.isGreenDelivery }

            deliveryTimes.value =
                listOf(greenDeliveries, deliveryObjects.minus(greenDeliveries)).flatten()
        }

        return deliveryTimes

    }

    private fun findValidDates(product: Product): List<Date> {
        val productDates = product.deliveryDays
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")

        val validDeliveryDates = mutableListOf<Date>()

        val convertedValues = productDates.map { date -> parser.parse(date) }
        convertedValues.forEach { deliveryDate ->
            if (isDeliveryDateValid(deliveryDate, product.productType, product.daysInAdvance)) {
                validDeliveryDates.add(deliveryDate)
            }
        }

        return validDeliveryDates
    }

    private fun isDeliveryDateValid(
        deliveryDate: Date?,
        productType: String,
        daysInAdvance: Int
    ): Boolean {
        var isValid = false
        deliveryDate?.let {

            //remove dates with more than 14 days ahead
            val maximumDelivery = dateAfterDays(14)

            if (deliveryDate.onOrBefore(maximumDelivery)) {

                isValid = when (productType) {
                    ProductType.NORMAL.type ->
                        isFutureDateValid(daysInAdvance, deliveryDate)

                    ProductType.EXTERNAL.type ->
                        isFutureDateValid(5, deliveryDate)


                    ProductType.TEMPORARY.type ->
                        isFutureDateValid(daysInAdvance, deliveryDate) && deliveryDate.onOrBefore(
                            dateForUpcomingSunday()
                        )
                    else -> false
                }
            }
        }
        return isValid
    }

    private fun isGreenDelivery(date: Date): Boolean {
        return isDateInNextDays(3, date) && Random.nextBoolean()
    }

    private fun isDateInNextDays(daysInAdvance: Int, date: Date): Boolean {
        val dateAfterAdvanceDays = dateAfterDays(daysInAdvance)
        return date.onOrBefore(dateAfterAdvanceDays)
    }

    private fun isFutureDateValid(daysInAdvance: Int, date: Date): Boolean {
        val dateAfterAdvanceDays = dateAfterDays(daysInAdvance)
        return dateAfterAdvanceDays.onOrBefore(date)
    }

    private fun dateForUpcomingSunday(): Date {
        val mCalendar = Calendar.getInstance()
        var i = mCalendar.get(Calendar.WEEK_OF_MONTH)
        mCalendar.set(Calendar.WEEK_OF_MONTH, ++i)
        mCalendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
        return mCalendar.time
    }

    private fun dateAfterDays(days: Int): Date {
        val c = Calendar.getInstance()
        c.add(Calendar.DATE, days)
        return c.time
    }
}
