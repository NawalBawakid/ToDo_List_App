package com.example.todolistapp.model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.allTaskList


class TaskViewModel: ViewModel(){

    var checked: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    var task:MutableLiveData<String> = MutableLiveData<String>()
    var dueDate:MutableLiveData<String> = MutableLiveData<String>()
    var description:MutableLiveData<String> = MutableLiveData<String>()


    fun addTask(taskModel: TaskModel) {
        allTaskList.add(taskModel)
    }


    @RequiresApi(Build.VERSION_CODES.N)
    fun remove(taskModel: TaskModel){
      allTaskList.removeIf { it.task == taskModel.task }
    }


    fun disply(title:String){
        var item = allTaskList.find { it.task == title }
        task.value = item?.task
        description.value = item?.description
        dueDate.value = item?.dueDate
    }


    fun update(tasktitle: String){

        var item = allTaskList.indexOfFirst { it.task == tasktitle }
        var editTask = TaskModel(allTaskList[item].checked, task.value!!, dueDate.value!!, description.value!!)
        allTaskList[item] = editTask

    }
}
