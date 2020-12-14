package com.example.telegacom.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DatabaseChannelDao {
    @Query("select * from channelTable")
    fun getChannels(): LiveData<List<DatabaseChannel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg channels: DatabaseChannel)
}

@Database(entities = [DatabaseChannel::class], version = 1)
abstract class ChannelsDatabase : RoomDatabase() {
    abstract val channelDao: DatabaseChannelDao
}

private lateinit var INSTANCE: ChannelsDatabase

fun getDatabase(context: Context): ChannelsDatabase {
    synchronized(ChannelsDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                ChannelsDatabase::class.java,
                "channels").build()
        }
    }
    return INSTANCE
}