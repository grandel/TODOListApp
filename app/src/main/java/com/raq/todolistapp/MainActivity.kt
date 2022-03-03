package com.raq.todolistapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.raq.todolistapp.dialogs.EditTaskDialog
import com.raq.todolistapp.interfaces.OnItemClickListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnItemClickListener {


    lateinit var customAdapter: CustomAdapter
    lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val addTaskButton = findViewById<Button>(R.id.addTaskButton)
        addTaskButton.setOnClickListener { addTask() }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val taskList: ArrayList<Task> = ArrayList()

        for (i in 1..100) {
            taskList.add(Task("Task $i", "Description $i"))
        }
        customAdapter = CustomAdapter(taskList, this)
        layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = customAdapter
        recyclerView.layoutManager = layoutManager

    }

    override fun onItemClicked(task: Task, position: Int) {
        Log.d(this@MainActivity.toString(), "item clicked")

        Toast.makeText(this, "Clicked item ${task.title}", Toast.LENGTH_LONG).show()
        EditTaskDialog.show(this, task) { title, description ->
            customAdapter.editTask(task, position, title, description)
        }
    }

    override fun onItemButtonClicked(task: Task, position: Int) {
        Log.d(this@MainActivity.toString(), "item button clicked")
        val adapter = recyclerView.adapter as CustomAdapter

        val lastDeletedTask = task
        val lastDeletedTaskPosition = position

        adapter.deleteTask(task, position)
        Snackbar.make(root, "SnackBar", Snackbar.LENGTH_LONG).setAction("Undo") {
            addTask(task, position)
        }.show()

    }

    private fun addTask(task:Task, position:Int)
    {
        customAdapter.addTask(task, position)
        recyclerView.smoothScrollToPosition(position)
    }

    private fun addTask() {
        val editText = findViewById<EditText>(R.id.newItemTitle)
        val text: String = editText.text.toString()
        if (text.isNotEmpty()) {
            customAdapter.addTask(Task(text, ""), 0)
        }
    }
}