package com.example.myapplication.fragment.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.entities.Task
import com.example.myapplication.data.view_model.UserViewModel
import com.example.myapplication.databinding.ItemTaskBinding
import java.util.*

class TaskHolder(
    private val binding: ItemTaskBinding,
    private val userViewModel: UserViewModel,
    private val action: (Task) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Task){
        val time = item.timestamp.get(Calendar.HOUR).toString() + ":" + item.timestamp.get(Calendar.MINUTE).toString()
        binding.tvTask.text = item.text
        binding.tvTime.text = time
        binding.checkBoxTask.isChecked = item.isDode
        binding.checkBoxTask.setOnCheckedChangeListener {_, isChecked ->
            item.isDode = isChecked
            updateTask(item)
        }
        itemView.setOnClickListener {
            action(item)
        }
    }

    private fun updateTask(item: Task) {
        userViewModel.updateTask(item)
    }
}