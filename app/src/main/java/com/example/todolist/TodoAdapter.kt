package com.example.todolist
// this class will contain tha main logic for our app
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_todo.view.*

    class TodoAdapter(private val todos: MutableList<Todo>):     //1st step
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {    // 3rd step

    class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) //2nd step

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent,false))
        // converts the xml file to a view holder
        }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos(){
        todos.removeAll { todo -> todo.isChecked }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(txtViewTodoTitle: TextView, isChecked: Boolean){
        if(isChecked) {
            txtViewTodoTitle.paintFlags = txtViewTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else {
            txtViewTodoTitle.paintFlags = txtViewTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }


    // binds data of the todo list to the views of our list
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) { //5th step
        val currentTodo = todos[position]
        holder.itemView.apply {
            txtViewToDo.text = currentTodo.title
            checkBoxDone.isChecked = currentTodo.isChecked
            toggleStrikeThrough (txtViewToDo, currentTodo.isChecked)
            checkBoxDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(txtViewToDo, isChecked)
                currentTodo.isChecked = !currentTodo.isChecked
            }
        }
    }
    override fun getItemCount(): Int {      // 4th step
        return todos.size
        // so thr recycler view knows how many items to display
    }
}