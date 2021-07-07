package com.example.myapplication.data.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.data.dao.TaskDao
import com.example.myapplication.data.entities.Task

class TaskRepository(private val taskDao: TaskDao) {

    val getAllTasks: LiveData<List<Task>> = taskDao.getAllTasks()

    suspend fun addTask(task: Task){
        taskDao.addTask(task)
    }

    suspend fun findTaskById(uid: Int){
        taskDao.findTaskById(uid)
    }

    suspend fun loadTasksById(tasksIds: IntArray){
        taskDao.loadTasksById(tasksIds)
    }

    suspend fun updateTask(task: Task){
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task){
        taskDao.deleteTask(task)
    }
}
