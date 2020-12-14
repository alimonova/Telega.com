package com.example.telegacom.network

import com.example.telegacom.database.Channel
import com.example.telegacom.database.DatabaseChannel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkChannelContainer(val channels: List<ChannelProperty>)

@JsonClass(generateAdapter = true)
data class NetworkChannel(
    var id: Int = 0,
    var name: String = "",
    var description: String = "",
    var subscribers: Int = 0,
    var image: String = "",
    var price: Int = 0,
    var currency: Int = 0,
    var channelLink: String = "",
    var managerLink: String = ""
)

/**
 * Convert Network results to database objects
 */
fun NetworkChannelContainer.asDomainModel(): List<ChannelProperty> {
    return channels.map {
        ChannelProperty(
            id = it.id,
            name = it.name,
            description = it.description,
            subscribers = it.subscribers,
            price = it.price,
            currency = it.currency,
            channelImage = it.channelImage,
            channelLink = it.channelLink,
            managerLink = it.managerLink
        )
    }
}

fun NetworkChannelContainer.asDatabaseModel(): Array<DatabaseChannel> {
    return channels.map {
        DatabaseChannel(
            id = it.id,
            name = it.name,
            description = it.description,
            subscribers = it.subscribers,
            price = it.price,
            currency = it.currency,
            channelImage = it.channelImage,
            channelLink = it.channelLink,
            managerLink = it.managerLink
        )
    }.toTypedArray()
}