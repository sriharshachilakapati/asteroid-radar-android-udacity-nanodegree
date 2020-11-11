package com.udacity.asteroidradar.data.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.data.model.Asteroid

@Dao
interface AsteroidDao {
    @Query("SELECT * FROM asteroids ORDER BY Date(closeApproachDate)")
    fun getAsteroids(): LiveData<List<Asteroid>>

    @Query("DELETE FROM asteroids WHERE Date(closeApproachDate) < Date(:date)")
    fun clearAsteroidsBefore(date: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(asteroid: Asteroid)
}