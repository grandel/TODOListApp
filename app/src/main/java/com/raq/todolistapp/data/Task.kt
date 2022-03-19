package com.raq.todolistapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(var title: String, var description: String) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}
