package com.example.myapplication.fragment.settings

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.myapplication.R
import com.google.android.material.textfield.TextInputLayout


class MysettingsActivity : AppCompatActivity() {
    override fun onStart() {
        super.onStart()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_mysettings)

        loadData()

        findView()

        CreateNotificationChannel()

        emailChange()

        switch1!!.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                sendNotification()
            }

        }


        if (switch2!!.isChecked) {
            AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_YES
            )
        } else {
            AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO
            )
        }

        switch2!!.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES
                )
            } else {
                AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO
                )
            }
            saveData()
        }

    }

    private val CHANNEL_ID = "channel_id_example_01"
    private val notificationID = 101

    private var switch1:Switch?= null
    private var switch2:Switch?=null
    private var etEmail: EditText? = null
    private var tiEmail: TextInputLayout? = null
    private var btnLogin: Button? = null


    private fun emailChange(){
        btnLogin?.setOnClickListener {
            val email = etEmail?.text?.toString() ?: ""
            val NotValid = if (email.isEmpty()) {
                tiEmail?.error = getString(R.string.empty_field)
                true
            } else {
                false
            }
            if(NotValid){
                return@setOnClickListener
            }
//            insertDataToDatabase(email)
        }
    }

//    private fun insertDataToDatabase(email : String){
//        if(email.isNotEmpty()){
//            updateUser(email)
//        }
//    }

    private fun CreateNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notification title"
            val descriptionText = "Description Text"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification(){
        val builder = NotificationCompat.Builder(this,CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("ToDo")
            .setContentText("You have some to do things")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(this)){
            notify(notificationID,builder.build())
        }
    }

    private fun saveData() {
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            val sw_switch2=findViewById<Switch>(R.id.sw_switch2)
            putBoolean("BOOLEAN_KEY", sw_switch2.isChecked)
        }.apply()
        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show()
    }

    fun loadData() {
        val sw_switch2=findViewById<Switch>(R.id.sw_switch2)
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedBoolean=sharedPreferences.getBoolean("BOOLEAN_KEY",false)
        sw_switch2.isChecked = savedBoolean
    }

    private fun findView(){
        etEmail = findViewById(R.id.et_email)
        tiEmail = findViewById(R.id.ti_email)
        switch1 = findViewById(R.id.sw_switch1)
        switch2 = findViewById(R.id.sw_switch2)
        btnLogin = findViewById(R.id.btn_login)
    }
}