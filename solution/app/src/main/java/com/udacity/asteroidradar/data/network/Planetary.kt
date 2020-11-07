package com.udacity.asteroidradar.data.network

import com.udacity.asteroidradar.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Planetary {
    @GET("planetary/apod")
    suspend fun pictureOfTheDay(
        @Query("date") date: String,
        @Query("hd") hd: Boolean,
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY
    ): Response<ApodResponse>
}