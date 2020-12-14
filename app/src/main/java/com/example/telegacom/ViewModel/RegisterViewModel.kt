package com.example.telegacom.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.telegacom.database.User
import com.example.telegacom.database.UserDao
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class RegisterViewModel(
    val users: UserDao,
    application: Application
) : AndroidViewModel(application), CoroutineScope {

    private val job by lazy { Job() }
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    var current_user = MutableLiveData<User>()
    var was_checked = MutableLiveData<Boolean>()
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    //private var tonight = MutableLiveData<SleepNight?>()

    init {
        initialize()
        was_checked.value = false
    }

    private fun initialize() {
        viewModelScope.launch {}
    }

    private suspend fun clear() {
        users.clear()
    }

    fun register(email: String, password: String) {

        launch(Dispatchers.Main) {
            current_user.value = withContext(Dispatchers.Default) {
                val user_ = users.checkEmailUniqueness(email)
                if (user_ == null) {
                    val user = User()
                    user.Email = email
                    user.Password = password
                    users.insert(user)
                    users.checkEmailUniqueness(email)
                } else {
                    null
                }
            }
            was_checked.value = true
            Log.i("reg", current_user.value.toString())
        }
    }
}