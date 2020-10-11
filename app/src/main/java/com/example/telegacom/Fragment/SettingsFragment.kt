package com.example.telegacom.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.telegacom.R

class SettingsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?) : View?
    {
        val view = LayoutInflater.from(getActivity()).inflate(R.layout.settings_fragment, container, false);

        return view
    }

}