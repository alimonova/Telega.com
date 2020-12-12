package com.example.telegacom.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PurchaseRequestDao {
    @Insert
    fun insert(purchaseRequest: PurchaseRequest)

    @Update
    fun update(purchaseRequest: PurchaseRequest)

    @Query("SELECT * from PurchaseRequests WHERE Id = :key")
    fun get(key: Long): PurchaseRequest?

    @Query("DELETE from PurchaseRequests")
    fun clear()

    @Query("SELECT * FROM PurchaseRequests ORDER BY Id DESC")
    fun getAllUsers(): LiveData<List<PurchaseRequest>>

    @Query("SELECT * FROM PurchaseRequests WHERE UserId = :key")
    fun getPurchaseRequestsByUserId(key: Long): LiveData<List<PurchaseRequest>>

    @Query("SELECT * FROM PurchaseRequests WHERE PurchasePostId = :key")
    fun getPurchaseRequestsByPurchasePostId(key: Long): LiveData<List<PurchaseRequest>>
}