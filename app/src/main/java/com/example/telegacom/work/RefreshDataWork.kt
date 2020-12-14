package com.example.telegacom.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.telegacom.database.getDatabase
import com.example.telegacom.repository.ChannelsRepository
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = ChannelsRepository(database)
        return try {
            repository.refreshChannels()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}