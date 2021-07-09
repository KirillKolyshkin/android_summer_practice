package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.data.UserDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //startActivityFromFragment(R.layout.fragment_login_light)


        setContentView(R.layout.fragment_login_light)
        setContentView(R.layout.fragment_main)

        val userDB = UserDatabase.getDatabase(this).userDao()



    }


}
