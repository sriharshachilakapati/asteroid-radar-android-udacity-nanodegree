package com.udacity.asteroidradar.data.network

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.data.Repository

class NetworkDataWorker(private val context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val repository = Repository(context)
        repository.refreshData()

        return Result.success()
    }
}