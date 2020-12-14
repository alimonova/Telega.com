package com.example.telegacom.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.telegacom.network.ChannelProperty

// TODO (02) Define extension function List<DatabaseVideo>.asDomainModel(), that returns
// a list of <Video>.
@Entity(tableName = "channelTable")
data class DatabaseChannel constructor(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val subscribers: Int,
    val price: Int,
    val currency: Int,
    val channelLink: String,
    val managerLink: String,
    val channelImage: String
)

fun List<DatabaseChannel>.asDomainModel(): List<ChannelProperty> {
    return map {
        ChannelProperty(
            id = it.id,
            name = it.name,
            description = it.description,
            subscribers = it.subscribers,
            price = it.price,
            currency = it.currency,
            channelImage = it.channelImage,
            channelLink = it.channelLink,
            managerLink = it.managerLink)
    }
}