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
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.data.UserDatabase
import com.example.myapplication.data.entities.Task
import com.example.myapplication.databinding.FragmentCalendarBinding
import com.example.myapplication.databinding.FragmentDialogBinding
import java.util.*
import kotlin.collections.HashMap

class CalendarFragment : Fragment() {

    private var binding : FragmentCalendarBinding? = null
    private var uid : Int =  0
    private var users: HashMap<String, List<Task>>? = null

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
        users = UserDatabase.getUserDatabase()

        binding?.calendarView?.setOnDateChangeListener { view, year, month, dayOfMonth ->
            showDialog(
                year,
                month,
                dayOfMonth
            )
        }
    }

    private fun showDialog(year : Int, month : Int, dayOfMonth : Int) {
        var tBinding = FragmentDialogBinding.inflate(LayoutInflater.from(requireContext()))

        AlertDialog.Builder(requireContext())
            .setView(R.layout.fragment_dialog)
            .setNeutralButton("Save") { dialog, _ ->
                if (tBinding?.etHour?.text?.toString() != null &&
                    tBinding?.etMinute?.text?.toString() != null &&
                    tBinding?.etTaskContent?.text?.toString() != null
                ) {
                    val hour = Integer.parseInt(tBinding.etHour.toString())
                    val minute = Integer.parseInt(tBinding.etMinute.toString())
                    val text = tBinding.etTaskContent.toString()
                    insertDataToDataBase(text, year, month, dayOfMonth, hour, minute)
                    Toast.makeText(requireContext(), "Save", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_calendarFragment_to_fragment_main)
                }
                else{
                    Toast.makeText(requireContext(), "Some fields are empty", Toast.LENGTH_LONG)
                        .show()
                }
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                findNavController().navigate(R.id.action_calendarFragment_to_fragment_main)
            }
            .show()

    }

    private fun insertDataToDataBase(task: String, year: Int, month: Int, dayOfMonth: Int, hour: Int, minute: Int) {
        users?.get(arguments?.get(ARG_USERNAME))?.toMutableList()?.add(
            Task(uid++, task, GregorianCalendar(year, month, dayOfMonth, hour, minute), false)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {

        private const val ARG_USERNAME = "ARG_USERNAME"

        fun createBundle(username: String): Bundle = Bundle().apply {
            putString(ARG_USERNAME, username)
        }
    }


}