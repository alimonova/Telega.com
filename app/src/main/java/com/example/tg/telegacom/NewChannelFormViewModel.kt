package com.example.tg.telegacom

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tg.telegacom.database.Channel
import com.example.tg.telegacom.database.ChannelDao
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class NewChannelFormViewModel(
    val channels: ChannelDao,
    application: Application
) : AndroidViewModel(application), CoroutineScope {

    private val job by lazy { Job() }
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    var new_channel = MutableLiveData<Channel?>()
    var was_inserted = MutableLiveData<Boolean>()
    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    init {
        was_inserted.value = false
    }

    //val saveButtonVisible = Transformations.map(new_channel) {
    //    null == it
    //}

    private suspend fun clear() {
        channels.clear()
    }

    fun add_channel(name: String, description: String, subscribers: Int, price: Double,
                    currency: Int, manager: String, channelLink: String, adminId: Long) {

        launch(Dispatchers.Main) {
            new_channel.value = withContext(Dispatchers.Default) {
                val channel = Channel()
                channel.Name = name
                channel.Description = description
                channel.Price = price
                channel.Currency = currency
                channel.AdminId = adminId
                channel.Subscribers = subscribers
                channel.Link = channelLink
                channel.Manager = manager
                channels.insert(channel)
            Log.i("new_chan", channel.Id.toString())
                channels.get(channel.Id)
            }
            was_inserted.value = true
            _showSnackbarEvent.value = true
        }
    }

    private var _showSnackbarEvent = MutableLiveData<Boolean>()

    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }
}