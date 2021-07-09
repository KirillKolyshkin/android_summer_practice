package com.example.myapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.data.dao.UserDao
import com.example.myapplication.data.entities.DateTypeConverter
import com.example.myapplication.data.entities.Task
import com.example.myapplication.data.entities.User

@Database(entities = [Task::class, User::class], version = 1, exportSchema = false)
@TypeConverters(DateTypeConverter::class)
abstract class UserDatabase : RoomDatabase(){
    abstract fun userDao() : UserDao

    companion object{
        @Volatile
        private var INSTANCE : UserDatabase? = null

        fun getDatabase(context: Context) : UserDatabase =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                UserDatabase::class.java,
                "user_database"
            ).build()
    }
}