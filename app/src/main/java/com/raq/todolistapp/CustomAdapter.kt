package com.raq.todolistapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.raq.todolistapp.interfaces.OnItemClickListener

class CustomAdapter(
    tasks: ArrayList<Task>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    private var taskList: ArrayList<Task> = ArrayList<Task>()
    private val TAG = this::class.simpleName

    fun deleteTask(task: Task, position: Int) {
        Log.d(this.javaClass.simpleName, "removed task")
        taskList.remove(task)
        this.notifyItemRemoved(position)
        this.notifyItemRangeChanged(position, taskList.size)
    }

    fun addTask(task: Task, position: Int) {
        Log.d(TAG, "added task")
        taskList.add(position, task)
        this.notifyItemInserted(position)

    }



    init {
        taskList = tasks
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(task: Task, position: Int, clickListener: OnItemClickListener) {
            itemView.setOnClickListener {
                clickListener.onItemClicked(task, position)
            }
            deleteButton.setOnClickListener {
                clickListener.onItemButtonClicked(task, position)
            }

        }

        val deleteButton: Button = view.findViewById(R.id.deleteButton)
        val textView: TextView = view.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.text_row_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = taskList[position].title
        viewHolder.bind(taskList[position], position, itemClickListener)
        Log.i(this.javaClass.toString(), "onBindViewHolder")
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun editTask(task: Task, position: Int, title: String, description: String) {
        task.title = title
        task.description = description
        taskList[position] = task
        this.notifyItemChanged(position)
    }
}