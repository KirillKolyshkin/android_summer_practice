package com.example.myapplication.fragment.main


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.data.UserDatabase
import com.example.myapplication.data.entities.Task
import com.example.myapplication.data.view_model.UserViewModel
import com.example.myapplication.fragment.main.adapter.TaskAdapter
import java.util.stream.Collector
import java.util.stream.Collectors

class MainFragment : Fragment() {

    //private var binding: FragmentMainLightBinding? = null
    private var adapter: TaskAdapter? = null
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainLightBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding?.tvDate =

        var tasks = userViewModel.getAllTasksOfUser(ARG_USERNAME)
        if(tasks != null)
            tasks = getTasksForCurrentDate(tasks)
        if(tasks != null) {
            adapter = TaskAdapter(tasks) { task ->
                showAlertDialog(task)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main_llt, menu)
        menu.getItem(R.id.username).title = arguments?.getString(ARG_USERNAME)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.loginFragment -> {
                findNavController().navigate(R.id.action_fragment_main_to_fragment_login)
                return true
            }
            R.id.settingsFragment -> {
                findNavController().navigate(R.id.action_fragment_main_to_fragment_settings)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun showAlertDialog(task: Task){

    }

    private fun getTasksForCurrentDate(tasks: List<Task>): List<Task>{
//        return tasks.stream().filter(
//            // compare task.timestamp with tvDate
//        ).collect(Collectors.toList())
        return tasks
    }

    companion object{

        private const val ARG_USERNAME = "ARG_USERNAME"

        fun createBundle(username: String): Bundle = Bundle().apply{
            putString(ARG_USERNAME, username)
        }
    }
}