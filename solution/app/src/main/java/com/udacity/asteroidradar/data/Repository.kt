package com.udacity.asteroidradar.data

import android.content.Context
import androidx.lifecycle.LiveData
import com.udacity.asteroidradar.*
import com.udacity.asteroidradar.data.model.Asteroid
import com.udacity.asteroidradar.data.model.PictureOfDay
import com.udacity.asteroidradar.data.network.Backend
import com.udacity.asteroidradar.data.network.parseAsteroidsJsonResult
import com.udacity.asteroidradar.data.persistence.AsteroidDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import org.json.JSONObject

/**
 * A repository to access planetary and NeoWS asteroid data.
 *
 * @param context The context to use to get the DB instance
 *
 * @author Sri Harsha Chilakapati
 */
class Repository(context: Context) {

    private val database = AsteroidDB.getInstance(context)

    /**
     * LiveData instance that emits asteroids of next [Constants.DEFAULT_END_DATE_DAYS] days.
     */
    val asteroids: LiveData<List<Asteroid>>
        get() = database.asteroidDao().getAsteroids()

    /**
     * LiveData instance that emits the [PictureOfDay] instance.
     */
    val pictureOfDay: LiveData<PictureOfDay?>
        get() = database.pictureOfDayDao().get()

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

        parsed.forEach(database.asteroidDao()::insert)
    }

    private suspend fun refreshPictureOfTheDay() {
        val response = Backend.planetary.pictureOfTheDay(getToday().formattedForPlanetaryAPI, false)

        if (!response.isSuccessful) {
            return
        }

        database.pictureOfDayDao().save(response.body()!!)
    }
}