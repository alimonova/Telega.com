package com.example.tg.telegacom.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "PurchaseRequests",
    foreignKeys = [
        ForeignKey(entity = User::class,
            parentColumns = arrayOf("Id"),
            childColumns = arrayOf("UserId"),
            onDelete = ForeignKey.CASCADE),

        ForeignKey(entity = PurchasePost::class,
            parentColumns = arrayOf("Id"),
            childColumns = arrayOf("PurchasePostId"),
            onDelete = ForeignKey.CASCADE),

        ForeignKey(entity = Channel::class,
            parentColumns = arrayOf("Id"),
            childColumns = arrayOf("ChannelId"),
            onDelete = ForeignKey.CASCADE)
    ]
)
data class PurchaseRequest(
    @PrimaryKey(autoGenerate = true)
    var Id: Long = 0L,

    @ColumnInfo(name = "UserId", index = true)
    var UserId: Long = 0L,

    @ColumnInfo(name = "PurchasePostId", index = true)
    var PurchasePostId: Long = 0L,

    @ColumnInfo(name = "ChannelId", index = true)
    var ChannelId: Long = 0L,

    @ColumnInfo(name = "Date")
    var Date: String = "",

    @ColumnInfo(name = "Message")
    var Message: String = "",

    @ColumnInfo(name = "Price")
    var Price: Double = 0.0,

    @ColumnInfo(name = "Currency")
    var Currency: Int = 0
)