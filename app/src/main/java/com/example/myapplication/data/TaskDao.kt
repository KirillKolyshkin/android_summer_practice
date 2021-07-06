package com.example.myapplication.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.data.entities.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getAll(): LiveData<List<Task>>

    @Query("SELECT * FROM tasks WHERE task_id IN (:taskIds) ORDER BY timestamp")
    fun loadByIds(taskIds: IntArray): List<Task>

    @Query("SELECT * FROM tasks WHERE task_id = :uid LIMIT 1")
    fun findById(uid: Int): Task

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)
}