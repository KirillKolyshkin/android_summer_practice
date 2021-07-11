package com.example.myapplication.fragment.main.add

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.data.entities.Task
import com.example.myapplication.data.view_model.TaskViewModel
import com.example.myapplication.databinding.FragmentCalendarBinding
import java.util.*

class CalendarFragment : Fragment() {

    private var calendarView : CalendarView? = null
    private var binding : FragmentCalendarBinding? = null
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(TASK_CONTENT)
        calendarView = view.findViewById(R.id.calendarView)
        calendarView?.setOnDateChangeListener { view, year, month, dayOfMonth -> showDialog(
            TASK_CONTENT ,year, month, dayOfMonth) }
    }

    private fun showDialog(taskContent: String , year : Int, month : Int, dayOfMonth : Int) {
        AlertDialog.Builder(requireContext())
            .setView(R.layout.fragment_dialog)
            .setPositiveButton("OK") { _, _ -> { view?.let {
                this.insertDataToDataBase(taskContent, it, year, month, dayOfMonth)
            } }}
            .setNegativeButton("CANCEL") { dialog, _ -> {dialog.dismiss()}}
    }

     private fun insertDataToDataBase(taskContent: String, view: View, year : Int, month : Int, dayOfMonth : Int) {
        if (taskContent.isNotEmpty()) {
            val task = Task(id, taskContent, getDate(year, month, dayOfMonth), false)
            taskViewModel.addTask(task)
            Toast.makeText(requireContext(), "Task $id has benn added", Toast.LENGTH_LONG).show()
            view.findNavController().navigate(R.id.action_addTaskFragment_to_fragment_main)
        } else {
            Toast.makeText(requireContext(), "Please, write your task", Toast.LENGTH_LONG).show()
        }
    }

    private fun getDate(year : Int, month : Int, dayOfMonth : Int) : Calendar{
        var calendar : Calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        return calendar
    }

    companion object {
        private const val TASK_CONTENT = "TASK_CONTENT"

        fun createBundle(taskContent: String) : Bundle = Bundle().apply{
            putString(TASK_CONTENT, taskContent)
        }
    }


}