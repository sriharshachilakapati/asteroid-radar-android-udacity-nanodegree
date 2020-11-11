package com.udacity.asteroidradar.data.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.udacity.asteroidradar.data.model.Asteroid
import com.udacity.asteroidradar.data.model.PictureOfDay

@Database(entities = [Asteroid::class, PictureOfDay::class], version = 1)
abstract class AsteroidDB : RoomDatabase() {

    abstract fun asteroidDao(): AsteroidDao

    abstract fun pictureOfDayDao(): PictureOfDayDao

    companion object {
        @Volatile
        private lateinit var INSTANCE: AsteroidDB

        fun getInstance(context: Context): AsteroidDB = synchronized(this) {
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(context, AsteroidDB::class.java, "app_database")
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return INSTANCE
        }
    }
}