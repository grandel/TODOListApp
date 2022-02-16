package com.raq.todolistapp.interfaces

import com.raq.todolistapp.Task

interface OnItemClickListener {
    fun onItemClicked(task: Task, position: Int)
    fun onItemButtonClicked(task: Task, position: Int)
}