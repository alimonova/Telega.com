package com.example.telegacom.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class User(
    @PrimaryKey(autoGenerate = true)
    var Id: Long = 0L,

    @ColumnInfo(name = "Nickname")
    var Nickname: String = "",

    @ColumnInfo(name = "Email")
    var Email: String = "",

    @ColumnInfo(name = "Password")
    var Password: String = ""
)