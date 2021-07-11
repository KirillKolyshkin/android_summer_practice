package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import com.example.myapplication.data.UserDatabase
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity()  {

    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)
        //val userDB = UserDatabase.getDatabase(this).userDao()
        controller = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment)
            .navController
    }

    override fun onSupportNavigateUp(): Boolean = controller.navigateUp()
}
