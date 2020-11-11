package com.udacity.asteroidradar.data.network

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.data.Repository

/**
 * A data worker class which is used to pre-fetch the data we need once a day.
 *
 * @property context The context to create the Repository instance.
 * @param params Any parameters to pass to the base class.
 *
 * @author Sri Harsha Chilakapati
 */
class NetworkDataWorker(private val context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val repository = Repository(context)
        repository.refreshData()

        return Result.success()
    }
}