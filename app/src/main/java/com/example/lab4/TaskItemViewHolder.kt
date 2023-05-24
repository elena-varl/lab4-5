package com.example.lab4

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4.databinding.FragmentNewTaskSheetBinding
import com.example.lab4.databinding.TaskItemCellBinding


class TaskItemViewHolder(
    private val context: Context,
    private val binding: TaskItemCellBinding


):RecyclerView.ViewHolder(binding.root) {
    fun bindTaskItem(taskItem:TaskItem){
        binding.name.text=taskItem.name
    }
}