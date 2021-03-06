package com.example.ayushtyagi.databseclass.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup



import com.example.ayushtyagi.databseclass.R
import com.example.ayushtyagi.databseclass.Task
import kotlinx.android.synthetic.main.item_list_task.view.*


class TaskRecyclerAdapter (
        val tasks: ArrayList<Task>,
        val onTaskUpdate : (task:Task) -> Unit,
        val onTaskDelete : (task:Task) -> Unit
): RecyclerView.Adapter<TaskRecyclerAdapter.TaskViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val li = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = li.inflate(R.layout.item_list_task, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        // holder.itemView.checkBox.isChecked = tasks[position].done
        holder.itemView.checkBox.setOnCheckedChangeListener(null)
        holder.itemView.checkBox.isChecked = tasks[position].done
        holder.itemView.tvTaskName.text = tasks[position].taskName
        holder.itemView.checkBox.setOnCheckedChangeListener {
            _, isChecked -> tasks[position].done = isChecked
            onTaskUpdate(tasks[position])
        }

    }

    class TaskViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }
}