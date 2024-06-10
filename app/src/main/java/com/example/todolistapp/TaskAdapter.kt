package com.example.todolistapp

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.concurrent.thread


class TaskAdapter(private var tasks: MutableList<Task>, private val database: AppDatabase) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    // Inner class for the ViewHolder
    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.task_title)
        val description: TextView = view.findViewById(R.id.task_description)
        val deleteButton: ImageButton = view.findViewById(R.id.delete_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }


    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.title.text = task.title
        holder.description.text = task.description

        // Set up the delete button click listener
        holder.deleteButton.setOnClickListener {
            removeTask(position)
        }
    }

    override fun getItemCount() = tasks.size

    // Method to update the list of tasks
    fun updateTasks(newTasks: List<Task>) {
        tasks.clear()
        tasks.addAll(newTasks)
        notifyDataSetChanged()
    }


    fun removeTask(position: Int) {
        val task = tasks[position]
        // Remove the task from the list
        tasks.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, tasks.size)

        // Delete the task from the database
        thread {
            database.taskDao().deleteTask(task)
            Handler(Looper.getMainLooper()).post {
                // Update the RecyclerView to reflect the changes
                notifyDataSetChanged()
            }
        }
    }




}



