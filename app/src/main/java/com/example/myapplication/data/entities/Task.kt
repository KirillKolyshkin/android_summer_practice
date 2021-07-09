package com.example.myapplication.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val taskId: Int,
    @ColumnInfo(name = "text")
    var text: String,
    @ColumnInfo(name = "timestamp")
    var timestamp: Calendar,
    @ColumnInfo(name = "state")
    var isDode: Boolean,
)
