package com.example.telegacom.Fragment

import android .content.Intent
import android.content.Intent.getIntent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.telegacom.Activity.MainActivity
import com.example.telegacom.ChannelDetailsViewModel
import com.example.telegacom.NewChannelFormViewModel
import com.example.telegacom.R
import com.example.telegacom.ViewModelFactory.ChannelDetailsViewModelFactory
import com.example.telegacom.ViewModelFactory.NewChannelFormViewModelFactory
import com.example.telegacom.database.TelegaDataBase
import com.example.telegacom.databinding.ChannelDetailsFragmentBinding
import com.example.telegacom.databinding.NewChannelFormFragmentBinding
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

class ChannelDetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireActivity().application
        val viewModelFactory = ChannelDetailsViewModelFactory(application)

        val channelDetailsViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(ChannelDetailsViewModel::class.java)

        val binding: ChannelDetailsFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.channel_details_fragment, container, false
        )

        channelDetailsViewModel.channelName = arguments?.getString("channelName").toString()
        channelDetailsViewModel.channelSubscribers =
            arguments?.getString("channelSubscribers").toString()
        channelDetailsViewModel.channelDescription = arguments?.getString("channelDescription").toString()
        channelDetailsViewModel.managerLink = arguments?.getString("managerLink").toString()
        channelDetailsViewModel.channelImage = arguments?.getString("channelImage").toString()
        channelDetailsViewModel.price = arguments?.getString("price").toString()
        Log.i("testtt1", channelDetailsViewModel.price)
        when {
            arguments?.getString("currency").toString() == "0" -> {
                channelDetailsViewModel.price += " USD"
            }
            arguments?.getString("currency").toString() == "1" -> {
                channelDetailsViewModel.price += " RUB"
            }
            arguments?.getString("currency").toString() == "2" -> {
                channelDetailsViewModel.price += " UAH"
            }
        }
        Log.i("testtt2", channelDetailsViewModel.price)

        Timber.i("onCreateView is called.")
        val view =
            LayoutInflater.from(getActivity()).inflate(R.layout.channel_details_fragment, container, false);

        binding.goToChannelBtn.setOnClickListener { view: View ->
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            intent.data = Uri.parse("https://" + arguments?.getString("channelLink").toString())
            startActivity(intent)
        }

        binding.backBtn.setOnClickListener { view: View ->
            view.findNavController().navigateUp()
        }

        binding.channelDetailsViewModel = channelDetailsViewModel

        return binding.root
    }
}