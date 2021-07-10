package com.example.myapplication.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.data.entities.Task
import com.example.myapplication.data.entities.User
import com.example.myapplication.data.entities.UserWithTasks

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: Task)

    @Query("SELECT * FROM user_table")
    fun getAllUsers() : LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE name = :name LIMIT 1")
    suspend fun findUserByName(name: String) : User

    @Query("SELECT * FROM tasks WHERE taskId = :task_id LIMIT 1")
    suspend fun findTaskById(task_id: Int) : Task

    @Transaction
    @Query("SELECT * FROM user_table WHERE name = :name LIMIT 1")
    suspend fun getTasksOfUser(name: String) : UserWithTasks

    @Update
    suspend fun updateUser(user: User)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Delete
    suspend fun deleteUser(user: User)
}