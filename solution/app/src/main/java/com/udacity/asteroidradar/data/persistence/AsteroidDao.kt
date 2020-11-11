package com.udacity.asteroidradar.data.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.data.model.Asteroid
import com.udacity.asteroidradar.formattedForNeoWS
import com.udacity.asteroidradar.getDateAfterNumDays
import com.udacity.asteroidradar.getToday

/**
 * Data Access Object interface to access the asteroid data from the room database.
 *
 * @author Sri Harsha Chilakapati
 */
@Dao
interface AsteroidDao {

    /**
     * Fetches all the saved asteroids between a [startDate] and [endDate] inclusive.
     *
     * @param startDate The inclusive starting date.
     * @param endDate   The inclusive ending date.
     *
     * @return A [LiveData] instance which allows continuous observing of new Asteroids.
     */
    @Query(
        """
            SELECT * FROM asteroids
                WHERE Date(:startDate) <= Date(closeApproachDate)
                  AND Date(:endDate) >= Date(closeApproachDate)
                ORDER BY Date(closeApproachDate)
        """
    )
    fun getAsteroids(startDate: String, endDate: String): LiveData<List<Asteroid>>

    /**
     * Deletes all the saved asteroids before the given [date].
     *
     * @param date The exclusive date.
     */
    @Query("DELETE FROM asteroids WHERE Date(closeApproachDate) < Date(:date)")
    fun clearAsteroidsBefore(date: String)

    /**
     * Inserts a new [asteroid] into the table, replacing it if already existing.
     *
     * @param asteroid The [Asteroid] object to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(asteroid: Asteroid)
}

fun AsteroidDao.getAsteroidsForCurrentWeek() = getAsteroids(
    getToday().formattedForNeoWS,
    getDateAfterNumDays(Constants.DEFAULT_END_DATE_DAYS).formattedForNeoWS
)

fun AsteroidDao.getAsteroidsForToday(): LiveData<List<Asteroid>> {
    val today = getToday().formattedForNeoWS
    return getAsteroids(today, today)
}