package com.raq.todolistapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raq.todolistapp.interfaces.OnItemClickListener

class MainActivity : AppCompatActivity(), OnItemClickListener {

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
        val customAdapter = CustomAdapter(taskList, this)

        recyclerView.adapter = customAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    override fun onItemClicked(task: Task, position: Int) {
        Log.d(this@MainActivity.toString(), "item clicked")

        Toast.makeText(this, "Clicked item ${task.title}", Toast.LENGTH_LONG).show()
        startActivity(createIntentForEditItemActivity(task))
    }

    fun createIntentForEditItemActivity(task: Task): Intent {
        var intent: Intent = Intent(this, EditItemActivity::class.java)
        intent.putExtra(EditItemActivity.TITLE, task.title)
        intent.putExtra(EditItemActivity.DESCRIPTION, task.description)
        return intent
    }


    override fun onItemButtonClicked(task: Task, position: Int) {
        Log.d(this@MainActivity.toString(), "item button clicked")
        val adapter = getRecyclerView().adapter as CustomAdapter
        adapter.deleteTask(task, position)
    }

    private fun getRecyclerView(): RecyclerView {
        return findViewById<RecyclerView>(R.id.recyclerView)
    }

    private fun addTask() {
        val adapter = getRecyclerView().adapter as CustomAdapter
        val editText = findViewById<EditText>(R.id.newItemTitle)
        var text: String = editText.text.toString()
        if (text.isNotEmpty()) {
            adapter.addTask(Task(text, ""), 0)
        }
    }

    fun notifyAdapter(position: Int) {
        val recyclerView = getRecyclerView()
        recyclerView.adapter?.notifyItemChanged(position)
    }

}