package com.raq.todolistapp.data

class TaskRepository(
    private val db: AppDatabase
) {
    fun getAll(): List<Task> = db.taskDao().getAll()

    fun insert(task: Task) = db.taskDao().insert(task)

    fun delete(task: Task) = db.taskDao().delete(task)

    fun update(task: Task) = db.taskDao().update(task)
}