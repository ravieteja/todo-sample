package com.sample.todolist.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.todolist.data.model.TodosModel
import kotlinx.coroutines.flow.Flow

@Dao
interface TodosDao {

    @Query("Select * from todos_table")
    suspend fun getAllTodos(): List<TodosModel>

    @Query("Select * from todos_table where todo_title like '%'||:query||'%'")
    suspend fun getTodosByTitle(query: String): List<TodosModel>

    @Delete
    suspend fun deleteTodo(todo: TodosModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTodo(todo: TodosModel): Long

}