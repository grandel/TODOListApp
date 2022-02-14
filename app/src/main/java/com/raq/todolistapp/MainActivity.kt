package com.raq.todolistapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val taskList: ArrayList<Task> = ArrayList()

        for (i in 1..100) {
            taskList.add(Task("Task $i", "Description $i"))
        }
        val customAdapter = CustomAdapter(taskList, this)

        recyclerView.adapter = customAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onItemClicked(task: Task) {
        Toast.makeText(this, "Clicked item ${task.title}", Toast.LENGTH_LONG).show()
        var intent: Intent = Intent(this, EditItemActivity::class.java)
        intent.putExtra(EditItemActivity.TITLE, task.title)
        intent.putExtra(EditItemActivity.DESCRIPTION, task.description)
        startActivity(intent)
    }

}