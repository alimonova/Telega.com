package com.example.telegacom.Fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.telegacom.R
import timber.log.Timber

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?) : View?
    {
        Timber.i("onCreateView is called.")
        val view = LayoutInflater.from(getActivity()).inflate(R.layout.home_fragment, container, false);

        return view
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