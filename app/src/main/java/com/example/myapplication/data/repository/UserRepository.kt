package com.example.myapplication.data.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.data.dao.UserDao
import com.example.myapplication.data.entities.Task
import com.example.myapplication.data.entities.User
import com.example.myapplication.data.entities.UserWithTasks

class UserRepository(private val userDao: UserDao) {

    val getAllUsers : LiveData<List<User>> = userDao.getAllUsers()

    suspend fun addUser(user : User){
        userDao.addUser(user)
    }

    suspend fun addTask(task: Task){
        userDao.addTask(task)
    }

    suspend fun getAllTasksOfUser(name: String): List<Task>?{
         return userDao.getTasksOfUser(name).tasks
    }


    suspend fun findUserByName(name: String): User{
        return userDao.findUserByName(name)
    }

    suspend fun findTaskById(task_id: Int): Task{
        return userDao.findTaskById(task_id)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }


    suspend fun updateTask(task: Task){
        userDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task){
        userDao.deleteTask(task)
    }

    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }

}