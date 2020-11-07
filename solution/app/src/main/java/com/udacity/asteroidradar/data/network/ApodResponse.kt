package com.udacity.asteroidradar.data.network

import com.squareup.moshi.Json

data class ApodResponse(
    @Json(name = "hdurl") val hdUrl: String,
    val url: String
)