package com.example.myapplication.data.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.data.dao.UserDao
import com.example.myapplication.data.entities.User

class UserRepository(private val userDao: UserDao) {
    val readAllData : LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user : User){
        userDao.addUser(user)
    }
}