package com.example.lab4

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.UUID

@Entity(tableName="task_item_table")
@RequiresApi(Build.VERSION_CODES.O)
class TaskItem (
    @ColumnInfo(name="name") var name:String,
    @ColumnInfo(name="description") var description:String,
    @ColumnInfo(name="dueTimeString") var dueTimeString:String?,
    @ColumnInfo(name="completedDateString") var completedDateString:String?,
    @PrimaryKey(autoGenerate = true)var id:Int=0
        ){

    fun isCompleted()=completedDate()!=null


     fun completedDate(): LocalDate?=
        if (completedDateString==null)
            null
        else LocalDate.parse(completedDateString, dateFormatter)

     fun dueTime(): LocalTime?=
        if (dueTimeString==null)
            null
        else LocalTime.parse(dueTimeString, timeFormatter)




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

   companion object {
       val timeFormatter:DateTimeFormatter= DateTimeFormatter.ISO_TIME
       val dateFormatter:DateTimeFormatter= DateTimeFormatter.ISO_DATE

   }
}