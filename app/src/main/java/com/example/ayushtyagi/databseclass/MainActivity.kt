package com.example.ayushtyagi.databseclass

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.ayushtyagi.databseclass.Adapters.TaskRecyclerAdapter
import com.example.ayushtyagi.databseclass.db.TaskTable
import com.example.ayushtyagi.databseclass.db.ToDoHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val tasks = ArrayList<Task>()
    lateinit var taskAdapter : TaskRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = ToDoHelper(this).writableDatabase
        rvTasks.layoutManager = LinearLayoutManager(this)
        fun refreshTodos(){
            tasks.clear()
            tasks.addAll(TaskTable.getAllTasks(db))
            taskAdapter.notifyDataSetChanged()

        }
        val onTaskUpdate = { task: Task ->
            TaskTable.updateTaskDone(db, task)
            refreshTodos()
        }

        val onTaskDelete = {
            task : Task -> TaskTable.deleteTask(db,task)
            refreshTodos()
        }


        taskAdapter = TaskRecyclerAdapter(tasks,onTaskUpdate,onTaskDelete)
        rvTasks.adapter = taskAdapter



        refreshTodos()
        btnAddTask.setOnClickListener {
            val newTask = Task(null,etNewTask.text.toString(),false)
          val id = TaskTable.addTask(db,newTask)

            refreshTodos()
            Log.d("Task","Inserted AT ${id}")

            taskAdapter.notifyDataSetChanged()
        }

        btnDelTask.setOnClickListener({
            TaskTable.deleteTask(db,true)
            refreshTodos()
        })



    }
}