package com.example.telegacom

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.telegacom.Fragment.CustomDialogFragment
import timber.log.Timber

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