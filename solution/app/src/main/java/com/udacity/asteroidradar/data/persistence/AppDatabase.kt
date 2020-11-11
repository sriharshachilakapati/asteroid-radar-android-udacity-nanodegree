package com.udacity.asteroidradar.data.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.udacity.asteroidradar.data.model.Asteroid
import com.udacity.asteroidradar.data.model.PictureOfDay

/**
 * The App's almighty database!!
 *
 * @author Sri Harsha Chilakapati
 */
@Database(entities = [Asteroid::class, PictureOfDay::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun asteroidDao(): AsteroidDao

    abstract fun pictureOfDayDao(): PictureOfDayDao

    companion object {
        @Volatile
        private lateinit var INSTANCE: AppDatabase

        fun getInstance(context: Context): AppDatabase = synchronized(this) {
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return INSTANCE
        }
    }
}