package com.example.tg.telegacom.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Channel::class, User::class, PurchasePost::class, PurchaseRequest::class], version = 1,  exportSchema = false)
abstract class TelegaDataBase : RoomDatabase() {
    abstract val ChannelDao: ChannelDao
    abstract val UserDao: UserDao
    abstract val PurchasePostDao: PurchasePostDao
    abstract val PurchaseRequestDao: PurchaseRequestDao

    companion object {
        @Volatile
        private var INSTANCE: TelegaDataBase? = null

        fun getInstance(context: Context): TelegaDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TelegaDataBase::class.java,
                        "telegacom"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }
}