package com.example.telegacom

import android.os.Handler
import android.util.Log
import androidx.lifecycle.*
import timber.log.Timber

const val KEY_TIMER_SECONDS = "timer_seconds_key"
const val KEY_TIMER_FOCUS_SECONDS = "timer_seconds_in_focus_key"
private val CORRECT_BUZZ_PATTERN = longArrayOf(100, 100, 100, 100, 100, 100)
private val PANIC_BUZZ_PATTERN = longArrayOf(0, 200)
private val GAME_OVER_BUZZ_PATTERN = longArrayOf(0, 2000)
private val NO_BUZZ_PATTERN = longArrayOf(0)

enum class BuzzType(val pattern: LongArray) {
    CORRECT(CORRECT_BUZZ_PATTERN),
    GAME_OVER(GAME_OVER_BUZZ_PATTERN),
    COUNTDOWN_PANIC(PANIC_BUZZ_PATTERN),
    NO_BUZZ(NO_BUZZ_PATTERN)
}

class MainViewModel(val testvalue: Int) : ViewModel() {
    private var _secondsInFocus = MutableLiveData<Int>()
    val secondsInFocus: LiveData<Int>
        get() = _secondsInFocus
    private var _forAMinuteOnFragment = MutableLiveData<Boolean>()
    val forAMinuteOnFragment: LiveData<Boolean>
        get() = _forAMinuteOnFragment
    private var handler = Handler()
    private lateinit var runnable: Runnable

    init {
        _secondsInFocus.value = 0;
        Log.i("AboutViewModel", "AboutViewModel created.")
        Log.i("TestValue", "TestValue = $testvalue.")
    }

    fun UpdateSeconds() {
        _secondsInFocus.value = 0;
    }

    fun forAMinuteOnFragment() {
        _forAMinuteOnFragment.value = false;
    }

    override fun onCleared() {
        super.onCleared()
        stopTimer()
        Log.i("AboutViewModel", "AboutViewModel destroyed.")
    }
    fun startTimer() {
        runnable = Runnable {
            _secondsInFocus.value = _secondsInFocus.value?.plus(1)

            if (_secondsInFocus.value!! % 60 == 0) {
                _forAMinuteOnFragment.value = true;
            }
            handler.postDelayed(runnable, 1000)
        }

        handler.postDelayed(runnable, 1000)
    }

    fun stopTimer() {
        handler.removeCallbacks(runnable)
    }

    ///////////////////////////APP WORKS///////////////////////////

    var secondsCount = 0
    private lateinit var runnableTotal: Runnable

    fun startTotalTimer() {
        runnableTotal = Runnable {
            secondsCount++
            Timber.i("App works: $secondsCount");
            handler.postDelayed(runnableTotal, 1000)
        }

        handler.postDelayed(runnableTotal, 1000)
    }

    fun stopTotalTimer() {
        handler.removeCallbacks(runnableTotal)
        var z : Double = secondsCountInFocus.toDouble() / secondsCount.toDouble() * 100.0;

        Timber.i("$secondsCountInFocus/$secondsCount секунд - час роботи MainActivity. $z% був у фокусі.");
    }

    ///////////////////////////APP WORKS IN FOCUS///////////////////////////

    var secondsCountInFocus = 0
    private lateinit var runnableInFocus: Runnable //how long app works in focus

    fun focusStartTimer() {
        runnableInFocus = Runnable {
            secondsCountInFocus++
            Timber.i("App works in focus: $secondsCountInFocus");
            handler.postDelayed(runnableInFocus, 1000)
        }

        handler.postDelayed(runnableInFocus, 1000)
    }

    fun focusStopTimer() {
        handler.removeCallbacks(runnableInFocus)
        var z : Double = secondsCountInFocus.toDouble() / secondsCount.toDouble() * 100.0;
        Timber.i("$secondsCountInFocus/$secondsCount секунд - час роботи MainActivity. $z% був у фокусі.");
    }
}