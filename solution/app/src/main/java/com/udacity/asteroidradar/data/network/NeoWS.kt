package com.udacity.asteroidradar.data.network

import com.udacity.asteroidradar.BuildConfig
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Backend API interface for the Near-earth-object Watcher Service from NASA.
 *
 * @author Sri Harsha Chilakapati
 */
interface NeoWS {
    @GET("neo/rest/v1/feed")
    suspend fun feed(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY
    ): Response<JSONObject>

    @GET("neo/rest/v1/lookup")
    suspend fun lookup(
        @Query("asteroid_id") asteroidId: Int,
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY
    ): Response<JSONObject>
}