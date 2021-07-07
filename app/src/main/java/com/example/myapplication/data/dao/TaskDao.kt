package com.example.myapplication.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.data.entities.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getAllTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM tasks WHERE task_id IN (:taskIds) ORDER BY timestamp")
    suspend fun loadTasksById(taskIds: IntArray): List<Task>

    @Query("SELECT * FROM tasks WHERE task_id = :uid LIMIT 1")
    suspend fun findTaskById(uid: Int): Task

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)
}
