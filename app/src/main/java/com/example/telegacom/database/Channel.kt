package com.example.telegacom.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.telegacom.database.User

@Entity(tableName = "Channels",
    foreignKeys = [ForeignKey(entity = User::class,
        parentColumns = arrayOf("Id"),
        childColumns = arrayOf("AdminId"),
        onDelete = ForeignKey.CASCADE)]
)
data class Channel(
    @PrimaryKey(autoGenerate = true)
    var Id: Long = 0L,

    @ColumnInfo(name = "Name")
    var Name: String = "",

    @ColumnInfo(name = "Subscribers")
    var Subscribers: Int = 0,

    @ColumnInfo(name = "Description")
    var Description: String = "",

    @ColumnInfo(name = "AdminId", index = true)
    var AdminId: Long = 0L,

    @ColumnInfo(name = "Price")
    var Price: Double = 0.0,

    @ColumnInfo(name = "Currency")
    var Currency: Int = 0,

    @ColumnInfo(name = "Manager")
    var Manager: String = "",

    @ColumnInfo(name = "Link")
    var Link: String = ""
)