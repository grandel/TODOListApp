package com.raq.todolistapp.interfaces

import androidx.room.*
import com.raq.todolistapp.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getAll(): List<Task>

    @Insert
    fun insertAll(vararg task: Task)

    @Delete
    fun delete(task: Task)

    @Update
    fun update(task: Task)
}