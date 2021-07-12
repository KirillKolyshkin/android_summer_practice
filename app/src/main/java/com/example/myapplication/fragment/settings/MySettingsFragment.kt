package com.example.myapplication.fragment.settings

import com.example.myapplication.R

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout


class MySettingsFragment : Fragment(R.layout.fragment_mysettings) {

    private val CHANNEL_ID = "channel_id_example_01"
    private val notificationID = 101

    private var switch1: Switch?= null
    private var switch2: Switch?=null
    private var etEmail: EditText? = null
    private var tiEmail: TextInputLayout? = null
    private var btnLogin: Button? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onCreate(savedInstanceState)

        loadData(view)

        findView(view)

        CreateNotificationChannel()

        emailChange()

        if(switch1!!.isChecked){
            sendNotification()
        }

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
            saveData(view)
        }
    }


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
                getActivity()?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification(){
        val builder = NotificationCompat.Builder(requireContext(),CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("ToDo")
            .setContentText("You have some to do things")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(requireContext())){
            notify(notificationID,builder.build())
        }
    }


    private fun saveData(view: View) {
        val sharedPreferences = this.requireActivity()
            .getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)


//      val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            val sw_switch1=view.findViewById<Switch>(R.id.sw_switch1)
            val sw_switch2=view.findViewById<Switch>(R.id.sw_switch2)

            putBoolean("BOOLEAN_KEY", sw_switch2.isChecked)
            putBoolean("BOOLEAN_KEY", sw_switch1.isChecked)
        }.apply()
    }

    fun loadData(view: View) {
        val sw_switch1=view.findViewById<Switch>(R.id.sw_switch1)
        val sw_switch2= view.findViewById<Switch>(R.id.sw_switch2)
        val sharedPreferences = this.requireActivity()
            .getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
//        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedBoolean=sharedPreferences.getBoolean("BOOLEAN_KEY",false)
        if (sw_switch2 != null) {
            sw_switch2.isChecked = savedBoolean
        }
        if (sw_switch1 != null) {
            sw_switch1.isChecked = savedBoolean
        }
    }

    private fun findView(view: View){
        etEmail = view.findViewById(R.id.et_email)
        tiEmail = view.findViewById(R.id.ti_email)
        switch1 = view.findViewById(R.id.sw_switch1)
        switch2 = view.findViewById(R.id.sw_switch2)
        btnLogin = view.findViewById(R.id.btn_login)
    }
}