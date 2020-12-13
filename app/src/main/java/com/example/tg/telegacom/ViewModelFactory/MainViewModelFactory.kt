package com.example.tg.telegacom.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tg.telegacom.ViewModel.MainViewModel
import java.lang.IllegalArgumentException

class MainViewModelFactory(private val finalTime: Int) : ViewModelProvider.Factory {
    override fun<T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(finalTime) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}