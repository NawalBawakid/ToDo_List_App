package com.example.todolistapp.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.model.TaskModel
import com.example.todolistapp.R
import com.example.todolistapp.toDoListFragmentDirections
import java.text.SimpleDateFormat
import java.util.*

class ToDoAdapter(private val dataset:List<TaskModel>): RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    class ToDoViewHolder(val view: View): RecyclerView.ViewHolder(view){
        var checkButton: CheckBox = view.findViewById(R.id.checkbutton)
        var task: TextView = view.findViewById(R.id.task)
        var dueDate: TextView = view.findViewById(R.id.dueDate)
        var taskList: ConstraintLayout = view.findViewById(R.id.tasklist)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        var todoLayout = LayoutInflater.from(parent.context).inflate(R.layout.listitem, parent, false)
        return ToDoViewHolder(todoLayout)
    }




    @SuppressLint("ResourceAsColor", "WeekBasedYear")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val item = dataset[position]
        holder.checkButton.isChecked
        holder.task.text = item.task
        holder.dueDate.text = item.dueDate




        var nowDate = Date()
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        var tasktime = formatter.parse(item.dueDate)

        if(tasktime.before(nowDate)) {
            holder.dueDate.setTextColor(Color.parseColor("#DC0909"))
        }




        holder.taskList.setOnClickListener{
            var action = toDoListFragmentDirections.actionToDoListFragmentToEditFragment(title = item.task)
            //var action = toDoListFragmentDirections.actionToDoListFragmentToEditAddPageFragment(title= item.task)
            holder.view.findNavController().navigate(action)
        }

    }




    override fun getItemCount(): Int {
        return dataset.size
    }
}