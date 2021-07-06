package com.example.myapplication.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val task_id: Int,
    @ColumnInfo(name = "text")
    var text: String,
    @ColumnInfo(name = "timestamp")
    var timestamp: Int,
    @ColumnInfo(name = "state")
    var isDode: Boolean,
)
