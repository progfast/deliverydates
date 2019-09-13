package com.streetmart.deliverytimes.util

import java.util.*

class Utils {
    companion object {
        fun dateForUpcomingSunday(): Date {
            val mCalendar = Calendar.getInstance()
            var i = mCalendar.get(Calendar.WEEK_OF_MONTH)
            mCalendar.set(Calendar.WEEK_OF_MONTH, ++i)
            mCalendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
            return mCalendar.time
        }

        fun dateAfterDays(days: Int): Date {
            val c = Calendar.getInstance()
            c.add(Calendar.DATE, days)
            return c.time
        }
    }
}