package com.example.myapplication.fragment.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.entities.Task
import com.example.myapplication.data.view_model.UserViewModel
import com.example.myapplication.databinding.ItemTaskBinding

class TaskAdapter(
    var list: List<Task>,
    private val userViewModel: UserViewModel,
    private val action: (Task) -> Unit
) : RecyclerView.Adapter<TaskHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskHolder = TaskHolder(
            ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            userViewModel,
            action
    )

    override fun onBindViewHolder(holder: TaskHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount(): Int = list.size
}