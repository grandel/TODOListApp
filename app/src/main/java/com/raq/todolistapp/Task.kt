package com.raq.todolistapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(var title: String, var description: String) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}
