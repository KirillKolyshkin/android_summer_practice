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
import com.example.myapplication.R
import com.example.myapplication.data.entities.Task
import com.example.myapplication.data.view_model.TaskViewModel
import java.util.*

class AddTaskFragment : Fragment() {

    private lateinit var taskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dialog, container, false)
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        val addButton: Button = view.findViewById(R.id.add_button)
        val taskContent: String = view.findViewById<EditText>(R.id.taskContent).toString()
        val timeStampStr : String = view.findViewById<EditText>(R.id.timeStamp).toString()
        //string -> timestamp

        addButton.setOnClickListener() {
            insertDataToDataBase(taskContent, view)
        }
        return view
    }

    private fun insertDataToDataBase(taskContent: String, view: View) {
        if (taskContent.isNotEmpty()) {
            val task = Task(id, taskContent, timestamp = Calendar.getInstance(), false)
            taskViewModel.addTask(task)
            Toast.makeText(requireContext(), "Task $id has benn added", Toast.LENGTH_LONG).show()
            view.findNavController().navigate(R.id.action_addTaskFragment_to_fragment_main)
        } else {
            Toast.makeText(requireContext(), "Please, write your task", Toast.LENGTH_LONG).show()
        }
    }
}