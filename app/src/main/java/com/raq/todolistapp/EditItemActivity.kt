package com.raq.todolistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.dialog.MaterialDialogs

class EditItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_item)

        val editItemTitle = findViewById<TextView>(R.id.editItemTitle)
        val editItemDescription = findViewById<TextView>(R.id.editItemDescription)
        editItemTitle.text = intent.extras?.getString(TITLE)
        editItemDescription.text = intent.extras?.getString(DESCRIPTION)
    }

    companion object {
        const val TITLE:String = "title"
        const val DESCRIPTION:String = "description"
    }
}