package com.example.tg.telegacom.Fragment

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context
import android.os.Bundle;
import androidx.fragment.app.DialogFragment
import timber.log.Timber


class CustomDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val message: String? = getArguments()?.getString("message")
        val title: String? = getArguments()?.getString("title")
        val builder: AlertDialog.Builder = AlertDialog.Builder(getActivity())
        return builder
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK", null)
            .create()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Timber.i("onActivityCreated is called.")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("onCreate is called.")
    }

    override fun onStart() {
        super.onStart()
        Timber.i("onStart is called.")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Timber.i("onAttach is called.")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume is called.")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.i("onDestroyView is called.")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy is called.")
    }

    override fun onDetach() {
        super.onDetach()
        Timber.i("onDetach is called.")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop is called.")
    }
}