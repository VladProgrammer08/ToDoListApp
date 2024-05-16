package com.example.todolistapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.todolistapp.R.id
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ShowTasksFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_show_tasks, container, false)
        val addButton = view.findViewById<Button>(R.id.add)
        addButton.setOnClickListener{
            view.findNavController()
                .navigate(R.id.action_showTasksFragment_to_addFragment)
        }
        return view
    }

}