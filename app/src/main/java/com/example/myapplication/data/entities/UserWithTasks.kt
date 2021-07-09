package com.example.myapplication.data.entities

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithTasks(
    @Embedded val user: User,
    @Relation(
        parentColumn = "name",
        entity = Task::class,
        entityColumn = "task_id"
    )
    var tasks: List<Task>
)
