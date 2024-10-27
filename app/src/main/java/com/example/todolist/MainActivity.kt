package com.example.todolist

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextTask: EditText
    private lateinit var buttonAddTask: Button
    private lateinit var listViewTasks: ListView
    private val tasks = ArrayList<Task>()
    private lateinit var adapter: ArrayAdapter<Task>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextTask = findViewById(R.id.editTextTask)
        buttonAddTask = findViewById(R.id.buttonAddTask)
        listViewTasks = findViewById(R.id.listViewTasks)

        adapter = object : ArrayAdapter<Task>(this, android.R.layout.simple_list_item_1, tasks) {
            override fun getView(position: Int, convertView: View?, parent: android.view.ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                val task = getItem(position)

                // Adiciona um riscado ao texto da tarefa se estiver concluída
                if (task != null && task.isCompleted) {
                    (view as TextView).paintFlags = view.paintFlags or android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
                } else {
                    (view as TextView).paintFlags = view.paintFlags and android.graphics.Paint.STRIKE_THRU_TEXT_FLAG.inv()
                }

                return view
            }
        }

        listViewTasks.adapter = adapter

        buttonAddTask.setOnClickListener {
            val taskName = editTextTask.text.toString()
            if (taskName.isNotEmpty()) {
                tasks.add(Task(taskName))
                adapter.notifyDataSetChanged()
                editTextTask.text.clear()
            }
        }

        // Ação de clicar na tarefa para marcar como concluída
        listViewTasks.setOnItemClickListener { _, _, position, _ ->
            val task = tasks[position]
            task.isCompleted = !task.isCompleted // Alterna o estado de conclusão
            adapter.notifyDataSetChanged()
        }
    }
}
