package com.example.telegacom.ViewModel

import android.os.Handler
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class RulesViewModel : ViewModel() {
    init {
        Log.i("RulesViewModel", "AboutViewModel created.")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("RulesViewModel", "AboutViewModel destroyed.")
    }
}