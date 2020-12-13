package com.example.telegacom

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.telegacom.database.Channel

class ChannelAdapter: RecyclerView.Adapter<TextItemViewHolder>()  {
    var data =  listOf<Channel>()

    // TODO (04) Override getItemCount() to return the total number of items in the data set.
    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.Name.toString()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}