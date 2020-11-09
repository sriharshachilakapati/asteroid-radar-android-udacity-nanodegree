package com.udacity.asteroidradar.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * An object to hold singleton instances of APIs used.
 *
 * @author Sri Harsha Chilakapati
 */
object Backend {
    /**
     * The Near-earth-object Watcher Service API from NASA.
     */
    val neoWS: NeoWS by lazy { retrofit.create(NeoWS::class.java) }

    /**
     * The Planetary API from NASA. Provides the image of the day.
     */
    val planetary: Planetary by lazy { retrofit.create(Planetary::class.java) }

    private val retrofit by lazy {
        // Create the HTTP client used by Retrofit, enabling logging only in debug build type.
        val okHttpClient = OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().apply {
                    setLevel(HttpLoggingInterceptor.Level.BODY)
                })
            }
        }.build()

        // Need an explicit Moshi instance to add Kotlin support.
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        // Build the Retrofit instance!
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
}