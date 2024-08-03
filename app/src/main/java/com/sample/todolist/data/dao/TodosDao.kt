package com.sample.todolist.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.sample.todolist.data.model.TodosModel

@Dao
interface TodosDao {

    @Query("Select * from todos_table")
    fun getAllTodos(): List<TodosModel>

    @Query("Select * from todos_table where todo_title like '%'||:query||'%' or todo_description like '%'||:query||'%'")
    fun getTodoByTitle(query: String): TodosModel

    @Query("Delete from todos_table where id=:id")
    fun deleteTodo(id: Int)

    @Query("Update todos_table set todo_title=:title, todo_description=:description where id=:id")
    fun updateTodo(id: Int, title: String, description: String)

    @Query("Insert into todos_table(todo_title, todo_description) values(:title, :description)")
    fun insertTodo(title: String, description: String)

}