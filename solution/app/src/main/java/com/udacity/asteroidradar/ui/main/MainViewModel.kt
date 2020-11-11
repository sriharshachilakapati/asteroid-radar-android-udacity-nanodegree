package com.udacity.asteroidradar.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.data.Repository
import kotlinx.coroutines.launch

class MainViewModel(
    repository: Repository,
    pictureOfTheDayEmptyCD: String,
    private val pictureOfTheDayCDFormat: String,
) : ViewModel() {

    private val asteroidFilter = MutableLiveData(AsteroidFilter.CURRENT_WEEK)

    val asteroids = Transformations.switchMap(asteroidFilter) {
        when (it!!) {
            AsteroidFilter.CURRENT_WEEK -> repository.asteroidsThisWeek
            AsteroidFilter.ONLY_TODAY -> repository.asteroidsToday
        }
    }

    val imageOfTheDayUrl = Transformations.map(repository.pictureOfDay) { it?.url }

    val imageOfTheDayContentDescription = Transformations.map(repository.pictureOfDay) { picture ->
        picture?.title?.let { pictureOfTheDayCDFormat.format(it) } ?: pictureOfTheDayEmptyCD
    }

    init {
        viewModelScope.launch { repository.refreshData() }
    }

    fun setAsteroidFilter(filter: AsteroidFilter) {
        asteroidFilter.postValue(filter)
    }
}

enum class AsteroidFilter {
    CURRENT_WEEK,
    ONLY_TODAY
}