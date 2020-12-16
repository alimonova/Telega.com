package com.example.telegacom.Fragment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.telegacom.databinding.ContactsFragmentBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.telegacom.Activity.MainActivity
import com.example.telegacom.ContactsViewModel
import com.example.telegacom.R
import timber.log.Timber


class ContactsFragment : Fragment() {
    private lateinit var viewModel : ContactsViewModel
    lateinit var fragmentTime : TextView
    private var myClipboard: ClipboardManager? = null
    private var myClip: ClipData? = null
    private lateinit var binding: ContactsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View?
    {
        binding = ContactsFragmentBinding.inflate(inflater)
        viewModel = ViewModelProviders.of(this).get(ContactsViewModel::class.java);
        binding.contactsViewModel = viewModel;

        Timber.i("onCreateView is called.")
        val view = binding.root

        fragmentTime = view.findViewById(R.id.fragment_time) as TextView

        (this.activity as MainActivity).viewModel.secondsInFocus.observe(viewLifecycleOwner, Observer { newTime ->
            var allTime = newTime;
            var hours: Int = allTime / 3600;
            allTime -= hours * 3600;
            var minutes: Int = allTime / 60;
            allTime -= minutes * 60;
            var seconds: Int = allTime;
            fragmentTime.text = "Вы на этой странице уже " + hours.toString() + "ч " + minutes.toString() + "м " + seconds.toString() + "с"
        })

        val facebook_btn : ImageButton = view.findViewById(R.id.facebook_link) as ImageButton
        val vk_btn : ImageButton = view.findViewById(R.id.vk_link) as ImageButton
        val instagram_btn : ImageButton = view.findViewById(R.id.instagram_link) as ImageButton

        facebook_btn.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            intent.data = Uri.parse("https://www.facebook.com/alex.burchinskaya00/")
            startActivity(intent)
        }

        vk_btn.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            intent.data = Uri.parse("https://vk.com/mardbm")
            startActivity(intent)
        }

        instagram_btn.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            intent.data = Uri.parse("https://www.instagram.com/mardbm_/")
            startActivity(intent)
        }
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