package com.sample.todolist.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("todos_table")
class TodosModel (
    @PrimaryKey(true) val id: Int? = null,
    @ColumnInfo("todo_title") val title: String,
    @ColumnInfo("todo_description") val description: String
)