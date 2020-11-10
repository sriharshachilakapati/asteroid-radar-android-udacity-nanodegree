package com.udacity.asteroidradar

import java.text.SimpleDateFormat
import java.util.*

private val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.ROOT)

private val defaultTimeZone = TimeZone.getDefault()
private val nasaTimeZone = TimeZone.getTimeZone("US/Eastern")

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
    get() = dateFormat.apply { timeZone = defaultTimeZone }.format(this)

/**
 * A helper property which formats the date in the Eastern time zone since that's where NASA
 * is located at and otherwise you might get a 400 error from their API because of non-existing
 * date (in their place).
 */
val Date.formattedForPlanetaryAPI: String
    get() = dateFormat.apply { timeZone = nasaTimeZone }.format(this)