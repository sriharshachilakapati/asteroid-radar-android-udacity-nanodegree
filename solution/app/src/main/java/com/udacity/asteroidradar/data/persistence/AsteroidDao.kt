package com.udacity.asteroidradar.data.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.data.model.Asteroid
import com.udacity.asteroidradar.formattedForNeoWS
import com.udacity.asteroidradar.getToday

@Dao
interface AsteroidDao {
    @Query(
        """
        SELECT * FROM asteroids
            WHERE Date(closeApproachDate) >= Date(:date)
            ORDER BY Date(closeApproachDate)
        """
    )
    fun getAsteroids(date: String = getToday().formattedForNeoWS): LiveData<List<Asteroid>>

    @Query("DELETE FROM asteroids WHERE Date(closeApproachDate) < Date(:date)")
    fun clearAsteroidsBefore(date: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(asteroid: Asteroid)
}