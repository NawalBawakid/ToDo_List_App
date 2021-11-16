package com.example.todolistapp

import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.todolistapp.model.TaskViewModel
import com.example.todolistapp.databinding.FragmentEditAddPageBinding
import com.example.todolistapp.model.TaskModel
import java.text.SimpleDateFormat
import java.util.*

class editAddPageFragment : Fragment() {

    private val viewModel: TaskViewModel by activityViewModels()
    private var _binding: FragmentEditAddPageBinding? = null
    private val binding get() = _binding!!

    //var formate = SimpleDateFormat("dd MMM, yyy", Locale.US)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditAddPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //viewModel.addTask(tasks, dates, descriptions)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.date.setOnClickListener { viewModel.dueDate() }

          //  var now = Calendar.getInstance()

            //val date = DateFormat.parse(date.text.toString())
            //now.time = date

           // var datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{view, Year, Month, DayOfMonth ->
             //   val selectedDate = Calendar.getInstance()
              //  selectedDate.set(Calendar.YEAR, Year)
             //   selectedDate.set(Calendar.MONTH, Month)
//                //val date = formate.format(selectedDate.time)
                //binding.date.text = DateFormat.format(selectedDate.day)
           // },
           // now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_WEEK))

          //  datePicker.show()
       //}


        binding.editaddbutton.setOnClickListener {
            Log.d("Ddd", "onViewCreated: ${viewModel.task.value} ${viewModel.date.value} ${viewModel.description.value}")

            if ((viewModel.task.value!!).isEmpty() || (viewModel.date.value!!).isEmpty() || (viewModel.description.value!!).isEmpty()){
                Toast.makeText(context, "Try Again", Toast.LENGTH_SHORT).show()
            }else{
                var action = editAddPageFragmentDirections.actionEditAddPageFragmentToToDoListFragment()
                view.findNavController().navigate(action)

                var task = TaskModel(false, viewModel.task.value!!, viewModel.date.value!!, viewModel.description.value!!)
                viewModel.addTask(task)
            }
        }

        binding.deletebutton.setOnClickListener {

            var action = editAddPageFragmentDirections.actionEditAddPageFragmentToToDoListFragment()
            view.findNavController().navigate(action)

            var deletTask = TaskModel(false, viewModel.task.value!!, viewModel.date.value!!, viewModel.description.value!!)
            viewModel.remove(deletTask)

          // Toast.makeText(requireContext(), "test", Toast.LENGTH_SHORT).show()

            //Toast.makeText(requireContext(), "${viewModel.}", Toast.LENGTH_SHORT).show()
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}