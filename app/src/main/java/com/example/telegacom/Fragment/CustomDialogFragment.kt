package com.example.telegacom.Fragment

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment
import com.example.telegacom.R


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
}