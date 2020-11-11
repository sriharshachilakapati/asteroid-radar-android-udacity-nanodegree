package com.udacity.asteroidradar.ui.main

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.data.Repository
import com.udacity.asteroidradar.ui.util.formatWith
import kotlinx.coroutines.launch

class MainViewModel(
    repository: Repository,
    pictureOfTheDayEmptyCD: String,
    private val pictureOfTheDayCDFormat: String,
) : ViewModel() {

    val asteroids = repository.asteroids

    val imageOfTheDayUrl = Transformations.map(repository.pictureOfDay) { it?.url }

    val imageOfTheDayContentDescription = Transformations.map(repository.pictureOfDay) {
        it?.title?.formatWith(pictureOfTheDayCDFormat) ?: pictureOfTheDayEmptyCD
    }

    init {
        viewModelScope.launch {
            repository.refreshData()
        }
    }

}