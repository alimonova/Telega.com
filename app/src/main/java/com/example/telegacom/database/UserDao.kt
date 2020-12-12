package com.example.telegacom.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Query("SELECT * from Users WHERE Id = :key")
    fun get(key: Long): User?

    @Query("DELETE from Users")
    fun clear()

    @Query("SELECT * FROM Users ORDER BY Id DESC")
    fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT * FROM Users WHERE Email = :email AND Password = :password")
    fun authorization(email: String, password: String): User?

    @Query("SELECT * FROM Users WHERE Email = :email")
    fun checkEmailUniqueness(email: String): User?
}