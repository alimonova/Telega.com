package com.example.telegacom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.telegacom.databinding.ListItemChannelBinding
import com.example.telegacom.network.ChannelProperty

class ChannelAdapter( val callback: ChannelClick) :
    RecyclerView.Adapter<ChannelViewHolder>()  {

    var channels: List<ChannelProperty> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelViewHolder {
        val withDataBinding: ListItemChannelBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ChannelViewHolder.LAYOUT,
            parent,
            false)
        return ChannelViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: ChannelViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.channelCallback = callback
            it.property = channels[position]
        }
    }

    override fun getItemCount() = channels.size

}

class ChannelViewHolder(val viewDataBinding: ListItemChannelBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.list_item_channel
    }
}

class ChannelClick(val block: (ChannelProperty) -> Unit) {
    fun onClick(channel: ChannelProperty) = block(channel)
}