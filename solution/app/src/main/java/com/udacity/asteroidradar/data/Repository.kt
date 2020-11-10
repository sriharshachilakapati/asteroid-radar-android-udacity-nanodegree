package com.udacity.asteroidradar.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.data.model.Asteroid
import com.udacity.asteroidradar.data.model.PictureOfDay
import com.udacity.asteroidradar.data.network.Backend
import com.udacity.asteroidradar.data.network.parseAsteroidsJsonResult
import com.udacity.asteroidradar.formattedForNeoWS
import com.udacity.asteroidradar.getDateAfterNumDays
import com.udacity.asteroidradar.getToday
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import org.json.JSONObject

/**
 * A repository to access planetary and NeoWS asteroid data.
 *
 * @author Sri Harsha Chilakapati
 */
class Repository {

    private val _asteroids = MutableLiveData<List<Asteroid>>()
    private val _pictureOfTheDay = MutableLiveData<PictureOfDay>()

    /**
     * LiveData instance that emits asteroids of next [Constants.DEFAULT_END_DATE_DAYS] days.
     */
    val asteroids: LiveData<List<Asteroid>>
        get() = _asteroids

    /**
     * LiveData instance that emits the [PictureOfDay] instance.
     */
    val pictureOfDay: LiveData<PictureOfDay>
        get() = _pictureOfTheDay

    /**
     * Make an API call and attempt to fetch the data from the network.
     */
    suspend fun refreshData() = withContext(Dispatchers.IO) {
        listOf(
            async { refreshAsteroidsData() },
            async { refreshPictureOfTheDay() }
        ).awaitAll()
    }

    private suspend fun refreshAsteroidsData() {
        val response = Backend.neoWS.feed(
            getToday().formattedForNeoWS,
            getDateAfterNumDays(Constants.DEFAULT_END_DATE_DAYS).formattedForNeoWS
        )

        if (!response.isSuccessful) {
            return
        }

        val body = JSONObject(response.body()!!)
        val parsed = parseAsteroidsJsonResult(body)
        _asteroids.postValue(parsed)
    }

    private suspend fun refreshPictureOfTheDay() {
        val response = Backend.planetary.pictureOfTheDay(getToday().formattedForNeoWS, true)

        if (!response.isSuccessful) {
            return
        }

        _pictureOfTheDay.postValue(response.body()!!)
    }
}