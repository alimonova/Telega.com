package com.example.telegacom.network

import com.squareup.moshi.Json

data class ChannelProperty(
    val id: Int,
    @Json(name = "subscribers")
    val subscribers: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "manager_link")
    val managerLink: String,
    @Json(name = "channel_link")
    val channelLink: String,
    @Json(name = "channel_image")
    val channelImage: String,
    @Json(name = "price")
    val price: Int,
    @Json(name = "currency")
    val currency: Int,
    @Json(name = "description")
    val description: String
)