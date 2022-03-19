package com.raq.todolistapp.dialogs

import android.content.Context
import android.view.LayoutInflater
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.raq.todolistapp.data.Task
import com.raq.todolistapp.databinding.EditTaskDialogBinding

class EditTaskDialog {
    companion object {

        fun show(
            context: Context,
            task: Task,
            onSuccess: (title: String, description: String) -> Unit
        ) {
            val binding: EditTaskDialogBinding =
                EditTaskDialogBinding.inflate(LayoutInflater.from(context))
            binding.editTextTaskTitle.setText(task.title)
            binding.editTextTaskDescription.setText(task.description)

            MaterialAlertDialogBuilder(context)
                .setTitle("Edit task").setView(binding.root)
                .setPositiveButton("Edit") { dialog, which ->
                    val title = binding.editTextTaskTitle.text.toString()
                    val description = binding.editTextTaskDescription.text.toString()
                    onSuccess(title, description)
                }.show()
        }

    }
}