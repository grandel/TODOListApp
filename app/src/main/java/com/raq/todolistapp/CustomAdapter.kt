package com.raq.todolistapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.raq.todolistapp.data.AppDatabase
import com.raq.todolistapp.data.Task
import com.raq.todolistapp.databinding.TextRowItemBinding
import com.raq.todolistapp.interfaces.OnItemClickListener

class CustomAdapter(
    tasks: ArrayList<Task>,
    private val context: Context
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    private var binding: TextRowItemBinding
    private var view: View

    private val itemClickListener: OnItemClickListener = context as OnItemClickListener
    private var taskList: ArrayList<Task> = ArrayList<Task>()
    private val TAG = this::class.simpleName


    init {
        taskList = tasks
        binding = TextRowItemBinding.inflate(LayoutInflater.from(context))
        view = binding.root
    }

    fun deleteTask(task: Task, position: Int) {
        Log.d(this.javaClass.simpleName, "removed task")
        taskList.remove(task)
        AppDatabase.getDatabase(context).taskDao().delete(task)
        this.notifyItemRemoved(position)
        this.notifyItemRangeChanged(position, taskList.size)
    }

    fun addTask(task: Task, position: Int) {
        Log.d(TAG, "added task")
        taskList.add(position, task)
        AppDatabase.getDatabase(context).taskDao().insert(task)
        this.notifyItemInserted(position)
    }

    fun updateTask(task: Task, position: Int, title: String, description: String) {
        task.title = title
        task.description = description
        taskList[position] = task
        AppDatabase.getDatabase(context).taskDao().update(task)
        this.notifyItemChanged(position)
    }

    fun setTasks(tasks: List<Task>) {
        taskList.clear()
        taskList.addAll(tasks)
        notifyDataSetChanged()
    }

    class ViewHolder(binding: TextRowItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val deleteButton: Button = binding.deleteButton

        val textView: TextView = binding.textView

        fun bind(task: Task, position: Int, clickListener: OnItemClickListener) {
            itemView.setOnClickListener {
                clickListener.onItemClicked(task, position)
            }
            deleteButton.setOnClickListener {
                clickListener.onItemButtonClicked(task, position)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = TextRowItemBinding.inflate(LayoutInflater.from(viewGroup.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = taskList[position].title
        viewHolder.bind(taskList[position], position, itemClickListener)
        Log.i(this.javaClass.toString(), "onBindViewHolder")
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}