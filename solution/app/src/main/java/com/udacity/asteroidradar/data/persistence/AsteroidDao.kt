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

@Dao
interface AsteroidDao {
    @Query(
        """
            SELECT * FROM asteroids
                WHERE Date(:startDate) <= Date(closeApproachDate)
                  AND Date(:endDate) >= Date(closeApproachDate)
                ORDER BY Date(closeApproachDate)
        """
    )
    fun getAsteroids(startDate: String, endDate: String): LiveData<List<Asteroid>>

    @Query("DELETE FROM asteroids WHERE Date(closeApproachDate) < Date(:date)")
    fun clearAsteroidsBefore(date: String)

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