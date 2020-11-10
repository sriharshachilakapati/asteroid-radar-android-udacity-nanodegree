package com.udacity.asteroidradar

import java.text.SimpleDateFormat
import java.util.*

private val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.ROOT)

/**
 * @return Today's date in the local time-zone.
 */
fun getToday(): Date = Calendar.getInstance().time

/**
 * A helper function to return the date after a specified number of [days] from today.
 *
 * @param days The number of days from today.
 * @return A [Date] instance.
 */
fun getDateAfterNumDays(days: Int): Date = with(Calendar.getInstance()) {
    add(Calendar.DAY_OF_YEAR, days)
    return@with time
}

/**
 * A helper property which formats the date according to NASA API requirements.
 */
val Date.formattedForNeoWS: String
    get() = dateFormat.format(this)