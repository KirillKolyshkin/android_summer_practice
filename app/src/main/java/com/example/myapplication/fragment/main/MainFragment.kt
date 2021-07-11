package com.example.myapplication.fragment.main

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.data.UserDatabase
import com.example.myapplication.data.dao.UserDao
import com.example.myapplication.data.entities.Task
import com.example.myapplication.databinding.FragmentMainBinding
//import com.example.myapplication.databinding.TaskEditingBinding
import java.util.*

class MainFragment : Fragment() {

    private var calendar: Calendar? = null
    private var year: Int? = null
    private var month: Int? = null
    private var day: Int? = null
    //private var taskBinding: TaskEditingBinding? = null
    private var binding: FragmentMainBinding? = null
    //private var adapter: TaskAdapter? = null
    //    private lateinit var userViewModel: UserViewModel
    private var database : UserDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding?.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        database = UserDatabase.getDatabase(requireContext()).userDao()
//
//        getCurrentDate()
//        val currentDate = day.toString() + "." + month.toString() + "." + day.toString()
//        binding?.tvDate?.text = currentDate
//
//        var percentOfCompletedTasks = "100%"
//        val tasks = getUserTasksForCurrentData()
//        if (tasks != null) {
//            percentOfCompletedTasks = getPercentOfCompletedTasks(tasks).toString() + "%"
//            adapter = TaskAdapter(tasks, database) { task ->
//                showAlertDialog(task)
//            }
//            binding?.rvTasks?.also {
//                it.layoutManager = LinearLayoutManager(requireContext())
//                it.adapter = adapter
//            }
//        }
//        binding?.tvPercents?.text = percentOfCompletedTasks
//    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//        inflater.inflate(R.menu.menu_main, menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.loginFragment -> {
//                findNavController().navigate(R.id.action_fragment_main_to_fragment_login)
//                true
//            }
//            R.id.settingsFragment -> {
//                findNavController().navigate(R.id.action_fragment_main_to_fragment_settings)
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        binding = null
//    }
//
//    private fun getPercentOfCompletedTasks(tasks: List<Task>): Int {
//        return tasks.filter { task -> task.isDode }
//            .count() * 100 / tasks.size
//    }
//
//    private fun showAlertDialog(task: Task) {
//        taskBinding = TaskEditingBinding.inflate(LayoutInflater.from(requireContext()))
//        setDataForEditText(task)
//
//        AlertDialog.Builder(requireContext())
//            .setView(R.layout.task_editing)
//            .setCancelable(true)
//            .setPositiveButton("Save") { dialog, _ ->
//                if (isAllDataWritten(taskBinding)) {
//                    updateData(taskBinding, task)
//                    Toast.makeText(requireContext(), "Changes saved", Toast.LENGTH_LONG).show()
//                    dialog.dismiss()
//                } else
//                    Toast.makeText(requireContext(), "Some fields are empty!", Toast.LENGTH_LONG)
//                        .show()
//            }
//            .setNegativeButton("Delete") { dialog, _ ->
//                database?.deleteTask(task)
//                Toast.makeText(requireContext(), "Task deleted", Toast.LENGTH_LONG).show()
//                dialog.dismiss()
//            }
//            .show()
//    }
//
//    private fun setDataForEditText(task: Task) {
//        taskBinding?.etHour?.setText(task.timestamp.get(Calendar.HOUR))
//        taskBinding?.etMinute?.setText(task.timestamp.get(Calendar.MINUTE))
//        taskBinding?.etDay?.setText(task.timestamp.get(Calendar.DAY_OF_MONTH))
//        taskBinding?.etMonth?.setText(task.timestamp.get(Calendar.MONTH))
//        taskBinding?.etYear?.setText(task.timestamp.get(Calendar.YEAR))
//    }
//
//    private fun updateData(binding: TaskEditingBinding?, task: Task) {
//        val newDate = GregorianCalendar()
//        newDate.set(
//            binding?.etYear?.text.toString().toInt(),
//            binding?.etMonth?.text.toString().toInt(),
//            binding?.etDay?.text.toString().toInt(),
//            binding?.etHour?.text.toString().toInt(),
//            binding?.etMinute?.text.toString().toInt()
//        )
//        task.timestamp = newDate
//        database?.updateTask(task)
//    }
//
//    private fun getUserTasksForCurrentData(): List<Task>? {
//        var tasks = database?.getTasksOfUser(arguments?.getString(ARG_USERNAME))?.tasks
//        if (tasks != null)
//            tasks = filterTasks(tasks)
//        return when (tasks?.size) {
//            0 -> null
//            else -> tasks
//        }
//    }
//
//    private fun filterTasks(tasks: List<Task>): List<Task> {
//        return tasks.filter { task ->
//            task.timestamp.get(Calendar.YEAR) == year &&
//                    task.timestamp.get(Calendar.MONTH) == month &&
//                    task.timestamp.get(Calendar.DAY_OF_MONTH) == day
//        }
//            .sortedBy { task -> task.timestamp }
//            .toList()
//    }
//
//    private fun isAllDataWritten(binding: TaskEditingBinding?): Boolean {
//        return (binding?.etHour?.text != null) &&
//                (binding?.etMinute?.text != null) &&
//                (binding?.etDay?.text != null) &&
//                (binding?.etMonth?.text != null) &&
//                (binding?.etYear?.text != null)
//    }
//
//    private fun getCurrentDate() {
//        calendar = Calendar.getInstance()
//        year = calendar?.get(Calendar.YEAR)
//        month = calendar?.get(Calendar.MONTH)
//        day = calendar?.get(Calendar.DAY_OF_MONTH)
//    }

//    companion object {
//
//        private const val ARG_USERNAME = "ARG_USERNAME"
//
//        fun createBundle(username: String): Bundle = Bundle().apply {
//            putString(ARG_USERNAME, username)
//        }
//    }
}