package com.example.myapplication.fragment.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.entities.Task
import com.example.myapplication.databinding.ItemTaskLtBinding

class TaskHolder(
    private val binding: ItemTaskLtBinding,
    private val action: (Task) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Task){
        with(binding) {
            binding.tvTask.text = item.text
            binding.checkBoxTask.isChecked = item.isDode
                    // NOT JUST TO STRING
            binding.tvTime.text = item.timestamp.toString()
                    // CONVERT TIME TO DATE!!!!!!!
        }
        itemView.setOnClickListener {
            action(item)
        }
    }
}