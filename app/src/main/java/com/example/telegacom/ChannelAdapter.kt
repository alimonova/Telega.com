package com.example.telegacom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.telegacom.databinding.ListItemChannelBinding
import com.example.telegacom.network.ChannelProperty

class ChannelAdapter( val onClickListener: OnClickListener ) :
    ListAdapter<ChannelProperty, ChannelAdapter.ChannelPropertyViewHolder>(DiffCallback) {

    class ChannelPropertyViewHolder(private var binding: ListItemChannelBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(channelProperty: ChannelProperty) {
            binding.property = channelProperty
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ChannelProperty>() {
        override fun areItemsTheSame(oldItem: ChannelProperty, newItem: ChannelProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ChannelProperty, newItem: ChannelProperty): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChannelPropertyViewHolder {
        return ChannelPropertyViewHolder(ListItemChannelBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ChannelPropertyViewHolder, position: Int) {
        val channelProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(channelProperty)
        }
        holder.bind(channelProperty)
    }

    class OnClickListener(val clickListener: (channelProperty: ChannelProperty) -> Unit) {
        fun onClick(channelProperty: ChannelProperty) = clickListener(channelProperty)
    }
}