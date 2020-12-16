package com.example.telegacom

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.telegacom.database.Channel
import com.example.telegacom.network.ChannelProperty


@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_baseline_broken_image_24))
            .into(imgView)
    }
}

@BindingAdapter("channelImage")
fun ImageView.setChannelImage(item: Channel?) {
    setImageResource(R.drawable.instagram)
}

@BindingAdapter("channelName")
fun TextView.setChannelName(item: String?) {
    text = item
}

@BindingAdapter("channelSubscribers")
fun TextView.setChannelSubscribers(item: Int?) {
    text = item?.toString()
}

@BindingAdapter("channelPrice")
fun TextView.setChannelPrice(item: ChannelProperty?) {
    var currency_ = "UAH"
    if (item?.currency == 0) {
        currency_ = "USD"
    }
    else if (item?.currency == 1) {
        currency_ = "RUB"
    }
    text = item?.price.toString() + " " + currency_
}