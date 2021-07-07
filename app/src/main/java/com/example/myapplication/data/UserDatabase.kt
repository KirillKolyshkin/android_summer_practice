package com.example.myapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.data.dao.UserDao
import com.example.myapplication.data.entities.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao

    companion object{
        @Volatile
        private var INSTANCE : UserDatabase?= null

        fun getDatabase(context: Context) : UserDatabase =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: buildDataBase(context).also {
                    INSTANCE = it
                }
            }

        private fun buildDataBase(context : Context) =
            Room.databaseBuilder(
                context.applicationContext,
                UserDatabase::class.java,
                "app_database"
            ).build()
    }

}