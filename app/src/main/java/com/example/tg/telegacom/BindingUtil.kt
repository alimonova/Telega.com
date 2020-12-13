package com.example.tg.telegacom

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.tg.telegacom.database.Channel

@BindingAdapter("channelImage")
fun ImageView.setChannelImage(item: Channel?) {
    item?.let {
        setImageResource(R.drawable.instagram)
    }
}

@BindingAdapter("channelName")
fun TextView.setChannelName(item: Channel?) {
    item?.let {
        text = item.Name
    }
}

@BindingAdapter("amountSubscribers")
fun TextView.setAmountSubscribers(item: Channel?) {
    item?.let {
        text = item.Subscribers.toString()
    }
}

@BindingAdapter("channelPrice")
fun TextView.setChannelPrice(item: Channel?) {
    item?.let {
        var currency = "";
        if (item.Currency == 0) {
            currency = "USD"
        }
        else if (item.Currency == 1) {
            currency = "RUB"
        }
        else {
            currency = "UAH"
        }

        text = item.Price.toString() + " " + currency
    }
}
