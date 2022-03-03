package com.raq.todolistapp.dialogs

import android.content.Context
import android.view.LayoutInflater
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.raq.todolistapp.R
import com.raq.todolistapp.Task
import kotlinx.android.synthetic.main.edit_task_dialog.view.*

class EditTaskDialog {
    companion object {

        fun show(
            context: Context,
            task: Task,
            onSuccess: (title: String, description: String) -> Unit
        ) {
            val view = LayoutInflater.from(context).inflate(R.layout.edit_task_dialog, null)
            view.editTextTaskTitle.setText(task.title)
            view.editTextTaskDescription.setText(task.description)

            MaterialAlertDialogBuilder(context)
                .setTitle("Edit task").setView(view)
                .setPositiveButton("Edit") { dialog, which ->
                    val title = view.editTextTaskTitle.text.toString()
                    val description = view.editTextTaskDescription.text.toString()
                    onSuccess(title, description)
                }.show()
        }

    }
}