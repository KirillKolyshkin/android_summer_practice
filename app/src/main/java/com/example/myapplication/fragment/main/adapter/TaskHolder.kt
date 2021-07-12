package com.example.myapplication.fragment.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.entities.Task
import com.example.myapplication.databinding.ItemTaskBinding
import java.util.*
import kotlin.collections.HashMap

class TaskHolder(
    private val binding: ItemTaskBinding,
    private val username: String?,
    private val users : HashMap<String, List<Task>>,
    private var tasks : List<Task>,
    private val action: (Task) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Task?){
        val time = item?.timestamp?.get(Calendar.HOUR).toString() + ":" + item?.timestamp?.get(Calendar.MINUTE).toString()
        binding.tvTask.text = item?.text
        binding.tvTime.text = time
        binding.checkBoxTask.isChecked = item?.isDode!!
        binding.checkBoxTask.setOnCheckedChangeListener {_, isChecked ->
            updateTask(item)
            item.isDode = isChecked
        }
        itemView.setOnClickListener {
            action(item)
        }
    }

    private fun updateTask(item: Task) {
        var tasks = users?.get(username)
        for(i in tasks?.indices!!){
            if(item == tasks[i]){
                tasks[i].isDode = !tasks[i].isDode
            }
            break
        }
    }
}