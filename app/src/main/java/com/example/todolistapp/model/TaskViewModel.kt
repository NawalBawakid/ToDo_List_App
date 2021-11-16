package com.example.todolistapp.model

import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolistapp.allTaskList
import com.example.todolistapp.databinding.FragmentEditAddPageBinding
import com.example.todolistapp.editAddPageFragment
import com.google.android.material.datepicker.MaterialDatePicker

class TaskViewModel: ViewModel(){

    var task:MutableLiveData<String> = MutableLiveData<String>()
    var date:MutableLiveData<String> = MutableLiveData<String>()
    var description:MutableLiveData<String> = MutableLiveData<String>()

    fun dueDate(){
        val datePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Select Date").setSelection(MaterialDatePicker.todayInUtcMilliseconds()).build()
        //datePicker.show()
    }

    fun addTask(taskModel: TaskModel) {
        allTaskList.add(taskModel)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun remove(taskModel: TaskModel){
        //Log.d(TAG, "remove: ${taskModel.task} ")
      allTaskList.removeIf { it.task == taskModel.task }
        //Log.d(TAG, "remove: ${allTaskList.size} -- ${a}")
    }
}