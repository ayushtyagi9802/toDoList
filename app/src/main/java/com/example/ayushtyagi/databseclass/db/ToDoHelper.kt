package com.example.ayushtyagi.databseclass.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ToDoHelper(context: Context?):SQLiteOpenHelper(context,"todo.db",null,1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.let {
            it.execSQL(TaskTable.CMD_CREATE_TABLE)
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}