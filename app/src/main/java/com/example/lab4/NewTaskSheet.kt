package com.example.lab4

import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.example.lab4.databinding.FragmentNewTaskSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.LocalTime

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class NewTaskSheet(var taskItem:TaskItem?) : BottomSheetDialogFragment() {
private lateinit var binding:FragmentNewTaskSheetBinding
private lateinit var taskViewModel: TaskViewModel
private var dueTime:LocalTime?=null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity=requireActivity()
        if (taskItem!=null)
        {
            binding.taskTitle.text="Edit task"
            val editable= Editable.Factory.getInstance()
            binding.name.text=editable.newEditable(taskItem!!.name)
            binding.description.text=editable.newEditable(taskItem!!.description)
            if (taskItem!!.dueTime!=null)
                dueTime=taskItem!!.dueTime!!
            updateTimeButtonText()

        }
        else {
            binding.taskTitle.text = "New task"

        }

        taskViewModel=ViewModelProvider(activity).get(TaskViewModel::class.java)

        binding.saveButton.setOnClickListener{
            saveAction()
        }
        binding.timePickerButton.setOnClickListener{
            openTimePicker()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun openTimePicker() {
      if (dueTime==null)
      {
          dueTime= LocalTime.now()
      }
        val listener = TimePickerDialog.OnTimeSetListener{
            _, selectedHour,selectedMinute ->
            dueTime= LocalTime.of(selectedHour, selectedMinute)
            updateTimeButtonText()
        }
        val dialog=TimePickerDialog(activity, listener, dueTime!!.hour, dueTime!!.minute,true)
        dialog.setTitle("Task Due time")
        dialog.show()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateTimeButtonText() {
        binding.timePickerButton.text= String.format("%02d:%02d",dueTime!!.hour, dueTime!!.minute)
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
            val newTask=TaskItem(name, description, dueTime, null)
            taskViewModel.addTask(newTask)
        }
        else
        {
            taskViewModel.updateTask(taskItem!!.id, name, description, dueTime)
        }
        binding.name.setText("")
        binding.description.setText("")
        dismiss()

    }
}