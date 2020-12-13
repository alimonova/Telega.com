package com.example.tg.telegacom.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PurchasePostDao {
    @Insert
    fun insert(purchasePost: PurchasePost)

    @Update
    fun update(purchasePost: PurchasePost)

    @Query("SELECT * from PurchasePosts WHERE Id = :key")
    fun get(key: Long): PurchasePost?

    @Query("DELETE from PurchasePosts")
    fun clear()

    @Query("SELECT * FROM PurchasePosts ORDER BY Id DESC")
    fun getAllUsers(): LiveData<List<PurchasePost>>

    @Query("SELECT * FROM PurchasePosts WHERE UserId = :key")
    fun getPurchasePostsByUserId(key: Long): LiveData<List<PurchasePost>>
}