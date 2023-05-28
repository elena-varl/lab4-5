package com.example.lab4

import android.content.Context
import androidx.core.content.ContextCompat
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

class TaskItem (
    var name:String,
    var description:String,
    var dueTime:LocalTime?,
    var completedDate:LocalDate?,
    var id:UUID= UUID.randomUUID()
        ){
    fun isCompleted()=completedDate!=null
    fun imageResource():Int{
        if (isCompleted())
            return R.drawable.checked_24 else return R.drawable.unchecked_24
    }
    fun imageColor(context: Context):Int{
        if (isCompleted())
            return purple(context)
        else return black(context)

    }
    private fun purple(context:Context)=ContextCompat.getColor(context,R.color.purple_500)
    private fun black(context:Context)=ContextCompat.getColor(context,R.color.black)

}