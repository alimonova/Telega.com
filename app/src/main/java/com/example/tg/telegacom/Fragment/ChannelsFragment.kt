package com.example.tg.telegacom.Fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tg.telegacom.ChannelAdapter
import com.example.tg.telegacom.ChannelListener
import com.example.tg.telegacom.ViewModel.ChannelViewModel
import com.example.tg.telegacom.R
import com.example.tg.telegacom.ViewModelFactory.ChannelViewModelFactory
import com.example.tg.telegacom.database.TelegaDataBase
import com.example.tg.telegacom.databinding.ChannelsFragmentBinding
import timber.log.Timber

class ChannelsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: ChannelsFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.channels_fragment, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = TelegaDataBase.getInstance(application).ChannelDao

        val viewModelFactory = ChannelViewModelFactory(dataSource, application)

        val channelViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(ChannelViewModel::class.java)

        binding.channelViewModel = channelViewModel

        // binding.setLifecycleOwner(this)
        binding.lifecycleOwner = this

        val create_btn = binding.createBtn
        create_btn.setOnClickListener (
            object : View.OnClickListener {
                override fun onClick(v: View) {
                    activity!!.supportFragmentManager.beginTransaction().replace(
                        R.id.framelayout_main, NewChannelFormFragment(),
                        "Новый канал"
                    ).commit()
                }
        })

        val adapter = ChannelAdapter(ChannelListener { Id ->
            channelViewModel.onChannelClicked(Id)
        })

        binding.channelsList.adapter = adapter

        channelViewModel.channels_list.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.addHeaderAndSubmitList(it)
            }
        })

        Timber.i("onCreateView is called.")
        return binding.root
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