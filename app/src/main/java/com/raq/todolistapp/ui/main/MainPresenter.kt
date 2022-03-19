package com.raq.todolistapp.ui.main

import com.raq.todolistapp.data.Task
import com.raq.todolistapp.data.TaskRepository

class MainPresenter(
    private val taskRepository: TaskRepository
) {

    fun getAllTasks(): List<Task> {
        return taskRepository.getAll()
    }
}