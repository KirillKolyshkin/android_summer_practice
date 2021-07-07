package com.example.myapplication.data.entities

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithTasks(
    @Embedded val user: User,
    @Relation(
        parentColumn = "name",
        entityColumn = "task_id"
    )
    val tasks: List<Task>
)
