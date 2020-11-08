package com.example.telegacom.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel

class ChannelViewModel : ViewModel() {
    init {
        Log.i("ChannelViewModel", "ChannelViewModel created.")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("ChannelViewModel", "ChannelViewModel destroyed.")
    }
}