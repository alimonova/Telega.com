package com.example.telegacom

import android.app.Application
import timber.log.Timber

class TelegaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}