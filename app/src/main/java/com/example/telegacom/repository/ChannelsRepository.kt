package com.example.telegacom.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.telegacom.database.ChannelsDatabase
import com.example.telegacom.database.DatabaseChannelDao
import com.example.telegacom.database.asDomainModel
import com.example.telegacom.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChannelsRepository(private val database: ChannelsDatabase) {

    val channels: LiveData<List<ChannelProperty>> =
        Transformations.map(database.channelDao.getChannels()) {
            it.asDomainModel()
        }

    suspend fun refreshChannels() {
        withContext(Dispatchers.IO) {
            val channels = TestApi.retrofitService.getProperties()[0]
            database.channelDao.insertAll(*channels.asDatabaseModel())
        }
    }
}