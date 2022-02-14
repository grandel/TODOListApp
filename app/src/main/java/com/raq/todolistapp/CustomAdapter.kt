package com.raq.todolistapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

interface OnItemClickListener {
    fun onItemClicked(task: Task)
}

class CustomAdapter(
    private val tasks: ArrayList<Task>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    private var taskList: ArrayList<Task> = ArrayList<Task>()

    init {
        taskList = tasks
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(task: Task, clickListener: OnItemClickListener) {
            itemView.setOnClickListener {
                clickListener.onItemClicked(task)
            }
        }

        val textView: TextView = view.findViewById(R.id.textView)


    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.text_row_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = taskList[position].title
        viewHolder.bind(taskList[position], itemClickListener)

    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}