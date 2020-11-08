package com.example.telegacom.Fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.telegacom.Activity.MainActivity
import com.example.telegacom.R
import com.example.telegacom.ViewModel.AboutViewModel
import timber.log.Timber


class AboutFragment : Fragment() {
    private lateinit var viewModel : AboutViewModel
    lateinit var fragmentTime : TextView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
        {
            viewModel = ViewModelProviders.of(this).get(AboutViewModel::class.java);
            Timber.i("onCreateView is called.")
            val view = LayoutInflater.from(getActivity()).inflate(
                R.layout.about_fragment,
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

    private fun getShareIntent() : Intent {
        return ShareCompat.IntentBuilder.from(activity!!)
            .setText("Я пользуюсь приложением Telega.com. Попробуй и ты!\n*ссылка на скачивание с PM*")
            .setType("text/plain")
            .intent
    }

    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.share_menu, menu)
        // check if the activity resolves
        if (null == getShareIntent().resolveActivity(requireActivity().packageManager)) {
            // hide the menu item if it doesn't resolve
            menu.findItem(R.id.share)?.isVisible = false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> shareSuccess  ()
        }
        return super.onOptionsItemSelected(item)
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