package com.example.myapplication.fragment.main.add

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentCalendarBinding

class CalendarFragment : Fragment() {

    private var calendarView : CalendarView? = null
    private var binding : FragmentCalendarBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalendarBinding.inflate(inflater, container, false)
        calendarView?.setOnDateChangeListener { view, year, month, dayOfMonth -> showDialog(year, month, dayOfMonth) }
        return binding?.root
    }

    private fun showDialog(year : Int, month : Int, dayOfMonth : Int) {
        AlertDialog.Builder(requireContext())
            //.setTitle(date???)
            .setView(R.layout.fragment_dialog)
            .setPositiveButton("OK") { dialog, _ -> {dialog.dismiss() } }
            .setNegativeButton("CANCEL") { dialog, _ -> {dialog.dismiss()}}
    }


}