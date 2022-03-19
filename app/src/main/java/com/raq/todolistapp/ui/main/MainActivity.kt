package com.raq.todolistapp.ui.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.raq.todolistapp.CustomAdapter
import com.raq.todolistapp.data.AppDatabase
import com.raq.todolistapp.data.Task
import com.raq.todolistapp.data.TaskRepository
import com.raq.todolistapp.databinding.ActivityMainBinding
import com.raq.todolistapp.dialogs.AddTaskDialog
import com.raq.todolistapp.dialogs.EditTaskDialog
import com.raq.todolistapp.interfaces.OnItemClickListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    lateinit var customAdapter: CustomAdapter
    lateinit var layoutManager: LinearLayoutManager

    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.floatingActionButton.setOnClickListener { addTask() }
        presenter = MainPresenter(TaskRepository(AppDatabase.getDatabase(this)))
        val recyclerView = binding.recyclerView

        GlobalScope.launch(Dispatchers.IO) {
            val taskList = presenter.getAllTasks()
            withContext(Dispatchers.Main) {
                customAdapter.setTasks(taskList)
            }
        }

        customAdapter = CustomAdapter(ArrayList(), this)
        layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = customAdapter
        recyclerView.layoutManager = layoutManager

    }


    override fun onItemClicked(task: Task, position: Int) {
        Log.d(this@MainActivity.toString(), "item clicked")

        Toast.makeText(this, "Clicked item ${task.title}", Toast.LENGTH_LONG).show()
        EditTaskDialog.show(this, task) { title, description ->
            customAdapter.updateTask(task, position, title, description)
        }
    }

    override fun onItemButtonClicked(task: Task, position: Int) {
        Log.d(this@MainActivity.toString(), "item button clicked")
        val adapter = binding.recyclerView.adapter as CustomAdapter

        adapter.deleteTask(task, position)
        Snackbar.make(binding.getRoot(), "SnackBar", Snackbar.LENGTH_LONG).setAction("Undo") {
            addTask(task, position)
        }.show()
    }

    private fun addTask(task: Task, position: Int) {
        customAdapter.addTask(task, position)
        binding.recyclerView.smoothScrollToPosition(position)
    }

    private fun addTask() {
        AddTaskDialog.show(this) { task ->

            customAdapter.addTask(task, 0)
        }

//        val title: String = binding.newItemTitle.text.toString()
//        if (title.isNotEmpty()) {
//            val newTask = Task(title, "")
//            customAdapter.addTask(newTask, 0)
//            binding.recyclerView.smoothScrollToPosition(0)
//        }
    }
}