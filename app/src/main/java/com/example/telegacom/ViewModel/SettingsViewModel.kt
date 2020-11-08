package com.example.telegacom.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {
    init {
        Log.i("SettingsViewModel", "SettingsViewModel created.")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("SettingsViewModel", "SettingsViewModel destroyed.")
    }
}