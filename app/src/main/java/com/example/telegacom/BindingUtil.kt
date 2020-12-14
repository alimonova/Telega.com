package com.example.telegacom

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.telegacom.database.Channel

@BindingAdapter("sleepImage")
fun ImageView.setSleepImage(item: Channel?) {
    setImageResource(R.drawable.instagram)
}

@BindingAdapter("sleepQualityString")
fun TextView.setSleepQualityString(item: Channel?) {

}

@BindingAdapter("channelImage")
fun ImageView.setChannelImage(item: Channel?) {
    setImageResource(R.drawable.instagram)
}

@BindingAdapter("channelName")
fun TextView.setChannelName(item: Channel?) {
    text = item?.Name.toString()
}

@BindingAdapter("channelSubscribers")
fun TextView.setChannelSubscribers(item: Channel?) {
    text = item?.Subscribers.toString()
}

@BindingAdapter("channelPrice")
fun TextView.setChannelPrice(item: Channel?) {
    var currency = "UAH"
    if (item?.Currency == 0) {
        currency = "USD"
    }
    else if (item?.Currency == 1) {
        currency = "RUB"
    }
    text = item?.Price.toString() + " " + currency
}