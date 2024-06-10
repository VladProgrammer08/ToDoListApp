package com.example.todolistapp

import androidx.lifecycle.Observer
import com.example.todolistapp.TaskAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.todolistapp.R.id
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ShowTasksFragment : Fragment() {

    private lateinit var tasksRecyclerView: RecyclerView
    private lateinit var taskAdapter: TaskAdapter
    private val database: AppDatabase by lazy {
        AppDatabase.getDatabase(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize the database instance here or in a ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_show_tasks, container, false)

        // Initialize RecyclerView
        tasksRecyclerView = view.findViewById(R.id.tasks_recycler_view)
        tasksRecyclerView.layoutManager = LinearLayoutManager(context)

        // Initialize the adapter with an empty list or data from your database
        taskAdapter = TaskAdapter(mutableListOf(), database)
        tasksRecyclerView.adapter = taskAdapter

        // Set up the 'Add' button to navigate to the AddFragment
        val addButton = view.findViewById<Button>(R.id.add)
        addButton.setOnClickListener {
            findNavController().navigate(R.id.action_showTasksFragment_to_addFragment)
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        // Refresh the list of tasks when returning to the fragment
        loadTasks()
    }

    private fun loadTasks() {
        database.taskDao().getAllTasks().observe(viewLifecycleOwner, Observer { tasks ->
            tasks?.let {
                taskAdapter.updateTasks(it)
            }
        })
    }
}


