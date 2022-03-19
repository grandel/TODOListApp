package com.raq.todolistapp.dialogs

import android.content.Context
import android.view.LayoutInflater
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.raq.todolistapp.data.Task
import com.raq.todolistapp.databinding.EditTaskDialogBinding

class AddTaskDialog {
    companion object {

        fun show(
            context: Context,
            onSuccess: (task: Task) -> Unit
        ) {
            val binding: EditTaskDialogBinding =
                EditTaskDialogBinding.inflate(LayoutInflater.from(context))

            MaterialAlertDialogBuilder(context)
                .setTitle("Add task").setView(binding.root)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok) { dialog, which ->
                    val title = binding.editTextTaskTitle.text.toString()
                    val description = binding.editTextTaskDescription.text.toString()
                    if (title.isNotEmpty()) {
                        onSuccess(Task(title, description))
                    }
                }.show()
        }

    }
}