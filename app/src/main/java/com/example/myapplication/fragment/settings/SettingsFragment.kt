package com.example.myapplication.fragment.settings

import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.data.entities.Task
import com.example.myapplication.databinding.UsernameEditingBinding
import com.example.myapplication.fragment.main.MainFragment

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private val CHANNEL_ID = "channel_id_example_01"
    private val notificationID = 101

    private var tvUsername: TextView?=null
    private var switch1: Switch?= null
    private var switch2: Switch?=null
    private var btnLogin: Button? = null
    private var users: HashMap<String, List<Task>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onCreate(savedInstanceState)

        loadData(view)
        findView(view)
        CreateNotificationChannel()
        emailChange()

        tvUsername?.text = arguments?.getString(ARG_USERNAME)

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_settings, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mainFragment -> {
                findNavController().navigate(
                    R.id.action_fragment_settings_to_fragment_main,
                    MainFragment.createBundle(tvUsername.toString())
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun emailChange(){
        val binding = UsernameEditingBinding.inflate(LayoutInflater.from(requireContext()))

        btnLogin?.setOnClickListener {
            val dialog = AlertDialog.Builder(requireContext())
                dialog.setView(R.layout.username_editing)

            dialog.setPositiveButton("Save"){ dialog, _ ->
                val newName = binding.etUsername.text.toString().replace(" ", "")
                if(newName != null){
                    updateUsername(tvUsername?.text.toString(), newName)
                    tvUsername?.text = newName
                    Toast.makeText(requireContext(), "Username updated", Toast.LENGTH_LONG)
                        .show()
                } else{
                    Toast.makeText(requireContext(), "Field is empty!", Toast.LENGTH_LONG)
                        .show()
                }
            }
            dialog.setNeutralButton("Cancel"){dialog, _ ->
                dialog.dismiss()
            }
            dialog.show()
        }
    }

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

//              val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            val sw_switch1=view.findViewById<Switch>(R.id.switch_notification)
            val sw_switch2=view.findViewById<Switch>(R.id.switch_darkmode)
            putBoolean("BOOLEAN_KEY", sw_switch2.isChecked)
            putBoolean("BOOLEAN_KEY", sw_switch1.isChecked)
        }.apply()
    }

    fun loadData(view: View) {
        val sw_switch1=view.findViewById<Switch>(R.id.switch_notification)
        val sw_switch2= view.findViewById<Switch>(R.id.switch_darkmode)
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

    private fun updateUsername(name: String, newName: String) {
        var tasks = users?.get(name)?.toMutableList()
        users?.remove(name)
        tasks?.let {
            users?.put(newName, it)
        }
    }

    private fun findView(view: View){
        tvUsername = view.findViewById(R.id.tv_username)
        switch1 = view.findViewById(R.id.switch_notification)
        switch2 = view.findViewById(R.id.switch_darkmode)
        btnLogin = view.findViewById(R.id.btn_name)
    }

    companion object {

        private const val ARG_USERNAME = "ARG_USERNAME"

        fun createBundle(username: String): Bundle = Bundle().apply {
            putString(ARG_USERNAME, username)
        }
    }
}
