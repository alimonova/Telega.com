package com.example.tg.telegacom.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.tg.telegacom.database.User
import com.example.tg.telegacom.database.UserDao
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LoginViewModel(
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
        was_checked.value = false
    }

    private suspend fun clear() {
        users.clear()
    }

    fun authorize(email: String, password: String) {

        launch(Dispatchers.Main) {
            current_user.value = withContext(Dispatchers.Default) {
                //val user = User()
                //user.Id = 1
                //user.Email = email
                //user.Password = password
                //users.insert(user)
                users.authorization(email, password)
            }
            was_checked.value = true
            Log.i("auth", current_user.value.toString())
        }
    }
}