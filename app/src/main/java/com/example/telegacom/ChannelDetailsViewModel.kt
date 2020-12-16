package com.example.telegacom

import android.app.Application
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.telegacom.database.Channel
import com.example.telegacom.database.ChannelDao
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ChannelDetailsViewModel(
    application: Application
) : AndroidViewModel(application), CoroutineScope {

    var new_channel = MutableLiveData<Channel?>()
    var was_inserted = MutableLiveData<Boolean>()
    var channelName : String = "test"
    var channelSubscribers : String = ""
    var channelDescription : String = ""
    var managerLink : String = ""
    var channelImage : String = ""
    var price : String = ""

    private val job by lazy { Job() }
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    init {
        was_inserted.value = false
    }


}