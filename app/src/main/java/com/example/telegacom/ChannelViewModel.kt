package com.example.telegacom

import android.app.Application
import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import androidx.lifecycle.*
import com.example.telegacom.database.Channel
import com.example.telegacom.database.ChannelDao
import com.example.telegacom.network.ChannelProperty
import com.example.telegacom.network.TestApi
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.viewModelScope
import com.example.telegacom.Fragment.SettingsFragment
import com.example.telegacom.database.getDatabase
import com.example.telegacom.network.NetworkChannelContainer
import com.example.telegacom.repository.ChannelsRepository
import kotlinx.coroutines.launch

class ChannelViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val channelsRepository = ChannelsRepository(database)

    init {
        viewModelScope.launch {
            channelsRepository.refreshChannels()
        }
    }

    val playlist = channelsRepository.channels
}
