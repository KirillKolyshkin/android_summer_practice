package com.example.myapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.data.dao.UserDao
import com.example.myapplication.data.entities.Task
import com.example.myapplication.data.entities.User

@Database(entities = [Task::class, User::class], version = 1, exportSchema = false)
@TypeConverters
abstract class UserDatabase : RoomDatabase(){
    abstract fun userDao() : UserDao

    companion object{
        @Volatile
        private var INSTANCE : UserDatabase? = null

        @Volatile
        private var INSTANCE_DB : HashMap<String, List<Task>>? = null

        fun getUserDatabase(): HashMap<String, List<Task>>? {
            if(INSTANCE != null)
                return INSTANCE_DB
            else{
                var db = hashMapOf<String, List<Task>>()
                INSTANCE_DB = db
                return db
            }
        }

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