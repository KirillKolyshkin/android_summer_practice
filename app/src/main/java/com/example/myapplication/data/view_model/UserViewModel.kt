package com.example.myapplication.data.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.UserDatabase
import com.example.myapplication.data.entities.User
import com.example.myapplication.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val readAllData : LiveData<List<User>>
    private val repository : UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser (user : User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }
}