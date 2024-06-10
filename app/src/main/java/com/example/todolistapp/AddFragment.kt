package com.example.todolistapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddFragment : Fragment() {

    private lateinit var repository: TaskRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        // Initialize the database and TaskDao
        val database = AppDatabase.getDatabase(requireActivity().application)
        val taskDao = database.taskDao()

        // Initialize your repository with the TaskDao
        repository = TaskRepository(taskDao)

        val titleEditText = view.findViewById<EditText>(R.id.task_title)
        val descriptionEditText = view.findViewById<EditText>(R.id.task_description)
        val addButton = view.findViewById<Button>(R.id.create)

        addButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val description = descriptionEditText.text.toString()

            // Create a new Task object
            val task = Task(title = title, description = description)

            // Insert the task into the database
            CoroutineScope(Dispatchers.IO).launch {
                repository.insertTask(task)
            }

            // Navigate back to the list of tasks
            view.findNavController().navigate(R.id.action_addFragment_to_showTasksFragment)
        }

        return view
    }
}

