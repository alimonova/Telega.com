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
import com.example.telegacom.database.User
import com.example.telegacom.database.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ChannelViewModel(
    val channels: ChannelDao,
    application: Application
) : AndroidViewModel(application) {


    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)
    //private var tonight = MutableLiveData<SleepNight?>()

    val channels_list = channels.getAllChannels()

    public val channelsString = Transformations.map(channels_list) { channels_list ->
        formatChannels(channels_list, application.resources)
    }

    init {
        initializeChannels()
    }

    private fun initializeChannels() {
        viewModelScope.launch {}
    }

    private suspend fun clear() {
        channels.clear()
    }

    private suspend fun update(channel: Channel) {
        channels.Update(channel)
    }

    private suspend fun insert(channel: Channel) {
        channels.insert(channel)
    }

    fun onChannelClicked (Id: Long) {

    }

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
