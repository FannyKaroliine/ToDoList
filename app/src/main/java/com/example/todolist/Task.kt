package com.example.todolist

data class Task(
    val name: String,
    var isCompleted: Boolean = false
) {
    override fun toString(): String {
        return name
    }
}
