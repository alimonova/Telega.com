package com.example.tg.telegacom.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel

class AboutViewModel : ViewModel() {
    init {
        Log.i("AboutViewModel", "AboutViewModel created.")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("AboutViewModel", "AboutViewModel destroyed.")
    }
}