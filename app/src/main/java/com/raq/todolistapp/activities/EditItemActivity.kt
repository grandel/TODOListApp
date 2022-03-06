package com.raq.todolistapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.raq.todolistapp.databinding.ActivityEditItemBinding

class EditItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditItemBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.editItemTitle.text = intent.extras?.getString(TITLE)
        binding.editItemDescription.text = intent.extras?.getString(DESCRIPTION)
    }

    companion object {
        const val TITLE: String = "title"
        const val DESCRIPTION: String = "description"
    }
}