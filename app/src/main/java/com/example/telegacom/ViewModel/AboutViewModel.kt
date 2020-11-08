package com.example.telegacom.ViewModel

import android.os.Handler
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class AboutViewModel : ViewModel() {
    init {
        Log.i("AboutViewModel", "AboutViewModel created.")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("AboutViewModel", "AboutViewModel destroyed.")
    }
}