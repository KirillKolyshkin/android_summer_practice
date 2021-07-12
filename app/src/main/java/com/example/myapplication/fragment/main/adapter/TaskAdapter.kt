package com.example.myapplication.fragment.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.entities.Task
import com.example.myapplication.databinding.ItemTaskBinding

class TaskAdapter(
    private var username: String?,
    private var users: HashMap<String, List<Task>>,
    private var tasks: List<Task>,
    private val action: (Task?) -> Unit
) : RecyclerView.Adapter<TaskHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskHolder = TaskHolder(
            ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            username,
            users,
            tasks,
            action
    )

    override fun onBindViewHolder(holder: TaskHolder, position: Int) = holder.bind(tasks[position])

    override fun getItemCount(): Int = tasks.size
}