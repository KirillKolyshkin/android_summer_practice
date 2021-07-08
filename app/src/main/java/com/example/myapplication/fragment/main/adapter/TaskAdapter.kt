package com.example.myapplication.fragment.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.entities.Task

class TaskAdapter(
    var list: List<Task>,
    private val action: (Task) -> Unit

) : RecyclerView.Adapter<TaskHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): TaskHolder = TaskHolder(

        )

    override fun onBindViewHolder(holder: TaskHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount(): Int = list.size
}