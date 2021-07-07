package com.example.myapplication.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "tasks",
        foreignKeys = [ForeignKey(entity = User::class,
            parentColumns = arrayOf("name"),
            childColumns = arrayOf("task_id"),
            onDelete = CASCADE)]
)
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
