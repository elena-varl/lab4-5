package com.example.lab4

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.lab4.databinding.FragmentNewTaskSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class NewTaskSheet(var taskItem:TaskItem?) : BottomSheetDialogFragment() {
private lateinit var binding:FragmentNewTaskSheetBinding
private lateinit var taskViewModel: TaskViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity=requireActivity()
        if (taskItem!=null)
        {
            binding.taskTitle.text="Edit task"
            val editable= Editable.Factory.getInstance()
            binding.name.text=editable.newEditable(taskItem!!.name)
            binding.description.text=editable.newEditable(taskItem!!.description)
        }
        else {
            binding.taskTitle.text = "New task"

        }

        taskViewModel=ViewModelProvider(activity).get(TaskViewModel::class.java)
        binding.saveButton.setOnClickListener{
            saveAction()
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentNewTaskSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun saveAction() {
     val name=binding.name.text.toString()
     val description=binding.description.text.toString()
        if (taskItem==null)
        {
            val newTask=TaskItem(name, description, null, null)
            taskViewModel.addTask(newTask)
        }
        else
        {
            taskViewModel.updateTask(taskItem!!.id, name, description, null)
        }
        binding.name.setText("")
        binding.description.setText("")
        dismiss()

    }
}