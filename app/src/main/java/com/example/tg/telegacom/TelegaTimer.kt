package com.example.tg.telegacom

import android.os.Handler
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import timber.log.Timber

class TelegaTimer(lifecycle: Lifecycle) : LifecycleObserver {

    /**
     * [Handler] is a class meant to process a queue of messages (known as [android.os.Message]s)
     * or actions (known as [Runnable]s)
     */
    private var handler = Handler()
    init {
        lifecycle.addObserver(this)
    }

    ///////////////////////////APP WORKS///////////////////////////

    var secondsCount = 0
    private lateinit var runnable: Runnable
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun startTimer() {
        runnable = Runnable {
            secondsCount++
            Timber.i("App works: $secondsCount");
            handler.postDelayed(runnable, 1000)
        }

        handler.postDelayed(runnable, 1000)
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun stopTimer() {
        handler.removeCallbacks(runnable)
        var z : Double = secondsCountInFocus.toDouble() / secondsCount.toDouble() * 100.0;

        Timber.i("$secondsCountInFocus/$secondsCount секунд - час роботи MainActivity. $z% був у фокусі.");
    }

    ///////////////////////////APP WORKS IN FOCUS///////////////////////////

    var secondsCountInFocus = 0
    private lateinit var runnableInFocus: Runnable //how long app works in focus

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun focusStartTimer() {
        runnableInFocus = Runnable {
            secondsCountInFocus++
            Timber.i("App works in focus: $secondsCountInFocus");
            handler.postDelayed(runnableInFocus, 1000)
        }

        handler.postDelayed(runnableInFocus, 1000)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun focusStopTimer() {
        handler.removeCallbacks(runnableInFocus)
        var z : Double = secondsCountInFocus.toDouble() / secondsCount.toDouble() * 100.0;
        Timber.i("$secondsCountInFocus/$secondsCount секунд - час роботи MainActivity. $z% був у фокусі.");
    }
}
