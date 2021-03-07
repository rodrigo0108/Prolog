package com.heyoh.prolog.feature.classes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heyoh.model.Task
import com.heyoh.prolog.databinding.ItemTaskBinding
import com.heyoh.prolog.util.extensions.diffUtil

class TaskAdapter (val onCLicked: (task: Task) -> Unit) :
        RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    var list: List<Task> by diffUtil(emptyList())

    inner class ViewHolder(private val binding: ItemTaskBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            with(binding) {
                taskTextView.text = task.name
                deliveryTextView.text = task.deliveryDate
                itemView.setOnClickListener {
                    onCLicked.invoke(task)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }
}