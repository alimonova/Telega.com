package com.example.telegacom.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ChannelDao {
    @Insert
    fun insert(channel: Channel)

    @Update
    fun Update(channel: Channel)

    @Query("SELECT * from channels WHERE Id = :key")
    fun get(key: Long): Channel?

    @Query("DELETE from channels")
    fun clear()

    @Query("SELECT * FROM channels ORDER BY Id DESC")
    fun getAllChannels(): LiveData<List<Channel>>

    @Query("SELECT * FROM channels WHERE AdminId = :userId")
    fun getChannelsByUserId(userId: Long): LiveData<List<Channel>>
}