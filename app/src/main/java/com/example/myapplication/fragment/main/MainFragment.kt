package com.example.myapplication.fragment.main


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.data.entities.Task
import com.example.myapplication.data.view_model.UserViewModel
import com.example.myapplication.databinding.FragmentMainBinding
import com.example.myapplication.fragment.main.adapter.TaskAdapter
import java.util.*

class MainFragment : Fragment() {

    private var calendar: Calendar? = null
    private var year: Int? = null
    private var month: Int? = null
    private var day: Int? = null
    private var binding: FragmentMainBinding? = null
    private var adapter: TaskAdapter? = null
    private lateinit var userViewModel : UserViewModel

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        getCurrentDate()
        val currentDate = day.toString() + "." + month.toString() + "." + day.toString()
        binding?.tvDate?.text = currentDate

        var tasks = getUserTasks()
        if (tasks != null) {
            adapter = TaskAdapter(tasks, userViewModel){ task ->
                showAlertDialog(task)
            }
            binding?.rvTasks?.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.adapter = adapter
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.loginFragment -> {
                findNavController().navigate(R.id.action_fragment_main_to_fragment_login)
                true
            }
            R.id.settingsFragment -> {
                findNavController().navigate(R.id.action_fragment_main_to_fragment_settings)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun showAlertDialog(task: Task) {
        // alert dialog for editing the task
    }

    private fun getUserTasks(): List<Task>?{
        var tasks = arguments?.getString(ARG_USERNAME)?.let {
            userViewModel.getAllTasksOfUser(it)
        }
        if (tasks != null) tasks = getTasksForCurrentDate(tasks)
        return when(tasks?.size){
            0 -> null
            else -> tasks
        }
    }

    private fun getTasksForCurrentDate(tasks: List<Task>): List<Task> {
        return tasks.filter { task ->
            task.timestamp.get(Calendar.YEAR) == year &&
                    task.timestamp.get(Calendar.MONTH) == month &&
                    task.timestamp.get(Calendar.DAY_OF_MONTH) == day
        }
            .sortedBy { task -> task.timestamp }
            .toList()
    }

    private fun getCurrentDate() {
        calendar = Calendar.getInstance()
        year = calendar?.get(Calendar.YEAR)
        month = calendar?.get(Calendar.MONTH)
        day = calendar?.get(Calendar.DAY_OF_MONTH)
    }

    companion object {

        private const val ARG_USERNAME = "ARG_USERNAME"

        fun createBundle(username: String): Bundle = Bundle().apply {
            putString(ARG_USERNAME, username)
        }
    }
}