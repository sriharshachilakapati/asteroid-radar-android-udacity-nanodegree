package com.udacity.asteroidradar.data.network

import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.data.model.PictureOfDay
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Backend API interface for the Planetary API from NASA.
 *
 * @author Sri Harsha Chilakapati
 */
interface Planetary {
    @GET("planetary/apod")
    suspend fun pictureOfTheDay(
        @Query("date") date: String,
        @Query("hd") hd: Boolean = false,
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY
    ): Response<PictureOfDay>
}