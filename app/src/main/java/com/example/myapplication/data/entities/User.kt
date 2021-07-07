package com.example.myapplication.data.entities


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class User(
    @ColumnInfo(name = "name")
    @PrimaryKey(autoGenerate = false)
    var name: String
)