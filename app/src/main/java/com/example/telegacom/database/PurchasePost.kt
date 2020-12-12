package com.example.telegacom.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "PurchasePosts",
        foreignKeys = [ForeignKey(entity = User::class,
        parentColumns = arrayOf("Id"),
        childColumns = arrayOf("UserId"),
        onDelete = ForeignKey.CASCADE)]
)
data class PurchasePost(
    @PrimaryKey(autoGenerate = true)
    var Id: Long = 0L,

    @ColumnInfo(name = "UserId", index = true)
    var UserId: Long = 0L,

    @ColumnInfo(name = "MinCoverage")
    var MinCoverage: Int = 0,

    @ColumnInfo(name = "Message")
    var Message: String = "",

    @ColumnInfo(name = "MinDate")
    var MinDate: String = "",

    @ColumnInfo(name = "MaxDate")
    var MaxDate: String = "",

    @ColumnInfo(name = "MaxPrice")
    var MaxPrice: String = "",

    @ColumnInfo(name = "Currency")
    var Currency: Int = 0
)