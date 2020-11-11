package com.udacity.asteroidradar.data.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.data.model.PictureOfDay

@Dao
interface PictureOfDayDao {
    @Query("SELECT * FROM picture_of_the_day WHERE id = 0")
    fun get(): LiveData<PictureOfDay?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(pictureOfDay: PictureOfDay)
}