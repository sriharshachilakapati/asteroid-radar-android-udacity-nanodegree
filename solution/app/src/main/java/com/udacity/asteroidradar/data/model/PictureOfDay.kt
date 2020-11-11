package com.udacity.asteroidradar.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "picture_of_the_day")
data class PictureOfDay(
    @PrimaryKey
    val id: Int = 0, // Always 0 since we want to save only one picture of the day!

    @Json(name = "media_type") val mediaType: String,
    val title: String,
    val url: String
)