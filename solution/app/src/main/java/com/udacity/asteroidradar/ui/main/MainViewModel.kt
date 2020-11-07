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

class MainViewModel : ViewModel() {
    private val _imageOfTheDayUrl = MutableLiveData<String>()

    val imageOfTheDayUrl: LiveData<String>
        get() = _imageOfTheDayUrl

    init {
        viewModelScope.launch {
            val today = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.ROOT).format(Date())
            val response = Backend.planetary.pictureOfTheDay(today, true)

            if (response.isSuccessful) {
                Timber.d("Response is %s", response.body())
                _imageOfTheDayUrl.value = response.body()!!.hdUrl
            }
        }
    }
}