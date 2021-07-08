package com.example.myapplication.data.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.UserDatabase
import com.example.myapplication.data.entities.Task
import com.example.myapplication.data.entities.User
import com.example.myapplication.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val getAllData : LiveData<List<User>>
    private val repository : UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        getAllData = repository.getAllUsers
    }

    fun addUser (user : User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun getAllTasksOfUser(name: String): List<Task>?{
        var tasks: List<Task>? = null
        viewModelScope.launch(Dispatchers.IO){
            tasks = repository.getAllTasksOfUser(name)
        }
        return tasks
    }

    fun findUserByName(name: String): User?{
        var user: User? = null
        viewModelScope.launch(Dispatchers.IO){
             user = repository.findUserByName(name)
        }
        return user
    }

    fun findTaskById(task_id: Int): Task?{
        var task: Task? = null
        viewModelScope.launch(Dispatchers.IO){
            task = repository.findTaskById(task_id)
        }
        return task
    }

    fun updateTask(task : Task){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateTask(task)
        }
    }

    fun addTask(task : Task){
        viewModelScope.launch(Dispatchers.IO){
            repository.addTask(task)
        }
    }

    fun updateUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateUser(user)
        }
    }

    fun deleteTask(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteTask(task)
        }
    }

    fun deleteUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteUser(user)
        }
    }
}