package com.example.myapplication.fragment.main.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.data.entities.Task
import com.example.myapplication.data.view_model.TaskViewModel
import java.util.*

class AddTaskFragment : Fragment(R.layout.fragment_dialog) {

    private lateinit var taskViewModel: TaskViewModel
    private var taskContent : String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        taskContent = view.findViewById<EditText>(R.id.taskContent).toString()

        findNavController().navigate(R.id.action_calendarFragment_to_addTaskFragment)
        CalendarFragment.createBundle(taskContent!!)
    }


}