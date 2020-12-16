package com.example.telegacom.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.telegacom.database.ChannelsDatabase
import com.example.telegacom.database.asDomainModel
import com.example.telegacom.network.ChannelProperty
import com.example.telegacom.network.TestApi
import com.example.telegacom.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChannelsRepository(private val database: ChannelsDatabase) {

    val channels: LiveData<List<ChannelProperty>> =
        Transformations.map(database.channelDao.getChannels()) {
            it.asDomainModel()
        }

    suspend fun refreshChannels() {
        withContext(Dispatchers.IO) {
            val channels = TestApi.retrofitService.getProperties()
            Log.i("api", channels.toString())
            database.channelDao.insertAll(*channels.asDatabaseModel())
        }
    }
}