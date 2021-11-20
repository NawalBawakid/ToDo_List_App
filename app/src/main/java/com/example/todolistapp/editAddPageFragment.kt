package com.example.todolistapp

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.todolistapp.model.TaskViewModel
import com.example.todolistapp.databinding.FragmentEditAddPageBinding
import com.example.todolistapp.model.TaskModel
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*

class editAddPageFragment : Fragment() {

    private val viewModel: TaskViewModel by activityViewModels()
    private var _binding: FragmentEditAddPageBinding? = null
    private val binding get() = _binding!!
    lateinit var currentTitle:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            currentTitle = it.getString("title")!!
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditAddPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.disply(currentTitle)

        binding.date.setOnClickListener {
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build()

                datePicker.addOnPositiveButtonClickListener {

                    val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                    calendar.time = Date(it)
                    binding.date.setText("${calendar.get(Calendar.DAY_OF_MONTH)}/" +
                            "${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.YEAR)}")
                }

                datePicker.show(parentFragmentManager, "MyTAG")
            }


        binding.editaddbutton.setOnClickListener {

            if ((viewModel.task.value.isNullOrEmpty()) || (viewModel.dueDate.value.isNullOrEmpty()) || (viewModel.description.value.isNullOrEmpty())){
                Toast.makeText(context, "Try Again", Toast.LENGTH_SHORT).show()
            }else if((viewModel.task.value.isNullOrEmpty()) && (viewModel.dueDate.value.isNullOrEmpty()) && (viewModel.description.value.isNullOrEmpty())){
                Toast.makeText(context, "Try Again", Toast.LENGTH_SHORT).show()
            } else{
                var action = editAddPageFragmentDirections.actionEditAddPageFragmentToToDoListFragment()
                view.findNavController().navigate(action)


                var task = TaskModel(false, viewModel.task.value!!, viewModel.dueDate.value!!, viewModel.description.value!!)
                viewModel.addTask(task)
            }
        }

        binding.deletebutton.setOnClickListener {
            var action = editAddPageFragmentDirections.actionEditAddPageFragmentToToDoListFragment()
            view.findNavController().navigate(action)

        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}