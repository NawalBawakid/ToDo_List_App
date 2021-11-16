package com.example.todolistapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.adapter.ToDoAdapter
import com.example.todolistapp.data.DataSource
import com.example.todolistapp.model.TaskModel
import com.example.todolistapp.model.TaskViewModel
import com.example.todolistapp.databinding.FragmentToDoListBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class toDoListFragment : Fragment() {

    private val viewModel: TaskViewModel by activityViewModels()
    private var _binding: FragmentToDoListBinding? = null
    private val binding get() = _binding!!
    private lateinit var task: List<TaskModel>
    private lateinit var recyclerView: RecyclerView
    private lateinit var currentList: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentToDoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recycleView
        task = DataSource().loadData()
        recyclerView.adapter = ToDoAdapter(task)

        binding.addbuttonmain.setOnClickListener{
            var action = toDoListFragmentDirections.actionToDoListFragmentToEditAddPageFragment(" "," "," ")
            view.findNavController().navigate(action)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}