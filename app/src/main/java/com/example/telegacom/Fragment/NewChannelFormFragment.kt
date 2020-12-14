package com.example.telegacom.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.telegacom.Activity.MainActivity
import com.example.telegacom.NewChannelFormViewModel
import com.example.telegacom.R
import com.example.telegacom.ViewModelFactory.NewChannelFormViewModelFactory
import com.example.telegacom.database.TelegaDataBase
import com.example.telegacom.databinding.NewChannelFormFragmentBinding
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

class NewChannelFormFragment  : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireActivity().application
        val dataSource = TelegaDataBase.getInstance(application).ChannelDao
        val viewModelFactory = NewChannelFormViewModelFactory(dataSource, application)

        val newChannelViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(NewChannelFormViewModel::class.java)

        val binding: NewChannelFormFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.new_channel_form_fragment, container, false
        )

        Timber.i("onCreateView is called.")
        val view =
            LayoutInflater.from(getActivity()).inflate(R.layout.new_channel_form_fragment, container, false);

        newChannelViewModel.showSnackBarEvent.observe(requireActivity(), Observer<Boolean> {
            if (it == true) { // Observed state is true.
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    "Запись успешно добавлена в базу данных",
                    Snackbar.LENGTH_SHORT // How long to display the message.
                ).show()

                newChannelViewModel.doneShowingSnackbar()
                requireActivity().supportFragmentManager.beginTransaction().replace(
                    R.id.framelayout_main, ChannelsFragment(),
                    "Каналы"
                ).commit()

            }
        })

        val save_btn = binding.saveBtn
        save_btn.setOnClickListener (
            object : View.OnClickListener {
                override fun onClick(v: View) {

                    var currency_id = 0
                    if (binding.rbRub.isChecked) {
                        currency_id = 1
                    } else if (binding.rbUah.isChecked) {
                        currency_id = 2
                    }

                    newChannelViewModel.add_channel(binding.channelName.text.toString(),
                        binding.description.text.toString(), binding.amountSubscribers.text.toString().toInt(),
                        binding.price.text.toString().toDouble(), currency_id,
                        binding.manager.text.toString(), binding.channelLink.text.toString(),
                        (activity!! as MainActivity).current_user_id.toLong())
                }
            })

        binding.cancelBtn.setOnClickListener (
            object : View.OnClickListener {
                override fun onClick(v: View) {
                    requireActivity().supportFragmentManager.beginTransaction().replace(
                        R.id.framelayout_main, ChannelsFragment(),
                        "Каналы"
                    ).commit()
                }
            })

        return binding.root
    }
}