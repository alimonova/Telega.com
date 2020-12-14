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
import com.example.telegacom.database.getDatabase
import com.example.telegacom.repository.ChannelsRepository
import kotlinx.coroutines.launch

class ChannelViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val channelsRepository = ChannelsRepository(database)

    private val _properties = MutableLiveData<List<ChannelProperty>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val properties: LiveData<List<ChannelProperty>>
        get() = _properties

    //val channels_list = channels.getAllChannels()

    //public val channelsString = Transformations.map(channels_list) { channels_list ->
    //    formatChannels(channels_list, application.resources)
    //}

    init {
        viewModelScope.launch {
            channelsRepository.refreshChannels()
        }
    }

    fun onChannelClicked(Id: Int) {

    }

    val channels_list = channelsRepository.channels

    fun formatChannels(channels: List<Channel>, resources: Resources): Spanned {
        val sb = StringBuilder()
        sb.apply {
            channels.forEach {
                append("<br>")
                append("<b>Канал</b>: ")
                append("\t${it.Name}<br>")
                append("<b>Подписчики</b>: ")
                append("\t${it.Subscribers}<br>")
                append("<b>Ссылка на канал</b>: ")
                append("\t${it.Link}<br>")
                append("<b>Описание</b>: ")
                append("\t${it.Description}<br>")
                append("<b>Менеджер</b>: ")
                append("\t${it.Manager}<br>")
                append("<b>Цена</b>: ")
                if (it.Currency == 0) {
                    append("\t${it.Price} USD<br>")
                } else if (it.Currency == 1) {
                    append("\t${it.Price} RUB<br>")
                } else {
                    append("\t${it.Price} UAH<br>")
                }
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
        } else {
            return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }
}
