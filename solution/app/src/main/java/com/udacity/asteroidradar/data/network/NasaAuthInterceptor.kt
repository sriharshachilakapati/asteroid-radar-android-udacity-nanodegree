package com.udacity.asteroidradar.data.network

import com.udacity.asteroidradar.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class NasaAuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newUrl = chain.request().url
            .newBuilder()
            .addQueryParameter("api_key", BuildConfig.NASA_API_KEY)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}