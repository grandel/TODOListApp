package com.raq.todolistapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(var title: String, var description: String) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}
