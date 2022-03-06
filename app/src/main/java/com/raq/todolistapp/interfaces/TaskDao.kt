package com.raq.todolistapp.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.raq.todolistapp.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM TASK")
    fun getAll(): List<Task>

    @Insert
    fun insertAll(vararg task: Task)

    @Delete
    fun delete(task: Task)
}