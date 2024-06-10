package com.example.todolistapp

import android.content.Context
import androidx.lifecycle.LiveData

class TaskRepository(private val taskDao: TaskDao) {


    suspend fun insertTask(task: Task) {
        taskDao.insertTask(task)
    }


    fun getAllTasks(): LiveData<List<Task>> {
        return taskDao.getAllTasks()
    }


}
