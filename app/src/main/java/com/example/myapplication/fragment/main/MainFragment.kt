package com.example.myapplication.fragment.main


import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.data.UserDatabase
import com.example.myapplication.data.view_model.UserViewModel
import com.example.myapplication.fragment.main.adapter.TaskAdapter

class MainFragment : Fragment() {

    private var binding: MainFragmentBinding? = null
    private var adapter: TaskAdapter? = null
    private lateinit var userViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username: String? = arguments?.getString(ARG_USERNAME)

        var tasks = userViewModel.getAllTasksOfUser(ARG_USERNAME)
        if(tasks != null) {
            adapter = TaskAdapter(tasks) { task ->

            }
        }

    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object{

        private const val ARG_USERNAME = "ARG_USERNAME"

        fun createBundle(username: String): Bundle = Bundle().apply{
            putString(ARG_USERNAME, username)
        }
    }
}