package com.udacity.asteroidradar.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.data.network.Backend
import kotlinx.coroutines.launch
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel(
    pictureOfTheDayEmptyCD: String,
    private val pictureOfTheDayCDFormat: String
) : ViewModel() {
    private val _imageOfTheDayUrl = MutableLiveData<String>()
    private val _imageOfTheDayContentDescription = MutableLiveData(pictureOfTheDayEmptyCD)

    val imageOfTheDayUrl: LiveData<String>
        get() = _imageOfTheDayUrl

    val imageOfTheDayContentDescription: LiveData<String>
        get() = _imageOfTheDayContentDescription

    init {
        viewModelScope.launch {
            val today =
                SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.ROOT).format(Date())
            val response = Backend.planetary.pictureOfTheDay(today, true)

            response.body()?.let {
                Timber.d("Response is decoded to %s", it)

                with(it) {
                    val contentDescription = String.format(pictureOfTheDayCDFormat, title)

                    _imageOfTheDayUrl.postValue(url)
                    _imageOfTheDayContentDescription.postValue(contentDescription)
                }
            }
        }
    }
}