package com.example.myapplication.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.data.entities.User


@Dao
interface UserDao {

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE id = :id")
    fun getById(id: Long): User?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User?)

    @Update
    fun update(user: User?)

    @Delete
    fun delete(user: User?)
}