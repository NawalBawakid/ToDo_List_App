package com.example.todolistapp.data

import com.example.todolistapp.allTaskList
import com.example.todolistapp.model.TaskModel

class DataSource {
    fun loadData(): MutableList<TaskModel>{
        return allTaskList
    }
}
