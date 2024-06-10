package com.example.todolistapp

import android.content.Context
import androidx.lifecycle.LiveData

class TaskRepository(private val taskDao: TaskDao) {

    // Function to insert a task into the database
    suspend fun insertTask(task: Task) {
        taskDao.insertTask(task)
    }

    // Function to get all tasks from the database
    fun getAllTasks(): LiveData<List<Task>> {
        return taskDao.getAllTasks()
    }

    // Add other database operations as needed
}
