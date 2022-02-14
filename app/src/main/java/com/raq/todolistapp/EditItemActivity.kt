package com.raq.todolistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class EditItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_item)

        var editItemTitle = findViewById<TextView>(R.id.editItemTitle)
        var editItemDescription = findViewById<TextView>(R.id.editItemDescription)
        editItemTitle.text = intent.extras?.getString(TITLE)
        editItemDescription.text = intent.extras?.getString(DESCRIPTION)
    }

    companion object {
        const val TITLE:String = "title"
        const val DESCRIPTION:String = "description"
    }
}