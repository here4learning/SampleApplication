package org.test.sample.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun getTimeInMillisBasedOnFormat(format : String,dob: String): Long {
    var timeInMillis = 0L
    try {
        val formatter = SimpleDateFormat(format, Locale.getDefault())
        timeInMillis = formatter.parse(dob)?.time ?: 0L
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return timeInMillis
}
