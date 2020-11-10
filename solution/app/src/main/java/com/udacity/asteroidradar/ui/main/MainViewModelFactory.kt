package com.udacity.asteroidradar.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.asteroidradar.data.Repository

class MainViewModelFactory(
    private val repository: Repository,
    private val pictureOfTheDayEmptyCD: String,
    private val pictureOfTheDayCDFormat: String
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository, pictureOfTheDayEmptyCD, pictureOfTheDayCDFormat) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}