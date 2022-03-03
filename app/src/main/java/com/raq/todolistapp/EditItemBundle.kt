package com.raq.todolistapp

import java.io.Serializable

data class EditItemBundle(
    val title: String,
    val description: String
): Serializable
