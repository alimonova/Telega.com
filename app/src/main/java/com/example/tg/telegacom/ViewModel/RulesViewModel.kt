package com.example.tg.telegacom.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel

class RulesViewModel : ViewModel() {
    init {
        Log.i("RulesViewModel", "AboutViewModel created.")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("RulesViewModel", "AboutViewModel destroyed.")
    }
}