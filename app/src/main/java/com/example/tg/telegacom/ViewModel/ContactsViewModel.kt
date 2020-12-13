package com.example.tg.telegacom.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel

class ContactsViewModel : ViewModel() {
    var email : String = "sburchinskaya.2000@gmail.com"
    var phone : String = "+380953953362"
    init {
        Log.i("ContactsViewModel", "ContactsViewModel created.")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("ContactsViewModel", "ContactsViewModel destroyed.")
    }

    fun CopyPhone() {
       Log.i("copy","You try to copy phone number. This functionality will be implemented later.");
    }

    fun CopyEmail() {
        Log.i("copy","You try to copy email. This functionality will be implemented later.");
    }
}