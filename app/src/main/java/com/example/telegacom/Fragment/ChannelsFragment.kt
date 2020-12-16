package com.example.telegacom.Fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.telegacom.ChannelAdapter
import com.example.telegacom.ChannelClick
import com.example.telegacom.ChannelViewModel
import com.example.telegacom.R
import com.example.telegacom.ViewModelFactory.ChannelViewModelFactory
import com.example.telegacom.database.TelegaDataBase
import com.example.telegacom.databinding.ChannelsFragmentBinding
import com.example.telegacom.network.ChannelProperty
import timber.log.Timber

class ChannelsFragment : Fragment() {

    private val viewModel: ChannelViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        //The ViewModelProviders (plural) is deprecated.
        //ViewModelProviders.of(this, DevByteViewModel.Factory(activity.application)).get(DevByteViewModel::class.java)
        ViewModelProvider(this, ChannelViewModelFactory(activity.application)).get(ChannelViewModel::class.java)

    }

    private var viewModelAdapter: ChannelAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.playlist.observe(viewLifecycleOwner, Observer<List<ChannelProperty>> { channels ->
            channels?.apply {
                viewModelAdapter?.channels = channels
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: ChannelsFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.channels_fragment,
            container,
            false)
        // Set the lifecycleOwner so DataBinding can observe LiveData
        binding.setLifecycleOwner(viewLifecycleOwner)

        binding.viewModel = viewModel

        viewModelAdapter = ChannelAdapter(ChannelClick {

            val bundle = bundleOf("channelName" to it.name,
                "channelSubscribers" to it.subscribers.toString(), "channelDescription" to it.description,
                "channelLink" to it.channelLink, "managerLink" to it.managerLink,
                "channelImage" to it.channelImage, "currency" to it.currency.toString(),
                "price" to it.price.toString())

            requireActivity().findNavController(R.id.myNavHostFragment).
            navigate(R.id.action_channelsFragment_to_channelDetailsFragment, bundle)
        })

        binding.root.findViewById<RecyclerView>(R.id.channels_list).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }

        //The complete onClickListener with Navigation
        binding.createBtn.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_channelsFragment_to_newChannelFormFragment)
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
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