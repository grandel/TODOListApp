package com.raq.todolistapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.raq.todolistapp.interfaces.TaskDao

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase = INSTANCE ?: createDatabase(context)

        private fun createDatabase(context: Context): AppDatabase {
            synchronized(this) {
                return Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "task-database"
                ).allowMainThreadQueries().build().also {
                    INSTANCE = it
                }
            }
        }
    }
}