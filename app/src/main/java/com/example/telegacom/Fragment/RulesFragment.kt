package com.example.telegacom.Fragment

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.telegacom.Activity.MainActivity
import com.example.telegacom.R
import com.example.telegacom.ViewModel.RulesViewModel
import timber.log.Timber


class RulesFragment : Fragment() {
    private lateinit var viewModel : RulesViewModel
    lateinit var fragmentTime : TextView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        viewModel = ViewModelProviders.of(this).get(RulesViewModel::class.java);
        Timber.i("onCreateView is called.")
        val view = LayoutInflater.from(getActivity()).inflate(
            R.layout.rules_fragment,
            container,
            false
        );

        fragmentTime = view.findViewById(R.id.fragment_time) as TextView

        (this.activity as MainActivity).viewModel.secondsInFocus.observe(this, Observer { newTime ->
            var allTime = newTime;
            var hours: Int = allTime / 3600;
            allTime -= hours * 3600;
            var minutes: Int = allTime / 60;
            allTime -= minutes * 60;
            var seconds: Int = allTime;
            fragmentTime.text = "Вы на этой странице уже " + hours.toString() + "ч " + minutes.toString() + "м " + seconds.toString() + "с"
        })

        setHasOptionsMenu(true)
        return view
    }

    private fun forAMinuteOnFragment() {
        Toast.makeText(this.activity, "Вы находились на этой странице в течение еще одной минуты.", Toast.LENGTH_SHORT).show()
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