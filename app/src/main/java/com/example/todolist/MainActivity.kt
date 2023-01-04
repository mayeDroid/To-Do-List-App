package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoAdapter = TodoAdapter(mutableListOf())
        recViewTodoItems.adapter = todoAdapter
        recViewTodoItems.layoutManager = LinearLayoutManager(this)

        btnTodo.setOnClickListener {
            val todoTitle = editTextTodoTitle.text.toString()
            if (todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                editTextTodoTitle.text.clear()
            }

            btnDeleteDoneTodo.setOnClickListener {
                todoAdapter.deleteDoneTodos()
            }
        }
    }
}