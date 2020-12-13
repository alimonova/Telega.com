package com.example.tg.telegacom.ViewModelFactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tg.telegacom.NewChannelFormViewModel
import com.example.tg.telegacom.database.ChannelDao

class NewChannelFormViewModelFactory(
    private val dataSource: ChannelDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewChannelFormViewModel::class.java)) {
            return NewChannelFormViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}