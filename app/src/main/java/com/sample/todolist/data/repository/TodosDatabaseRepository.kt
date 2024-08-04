package com.sample.todolist.data.repository

import com.sample.todolist.data.model.TodosModel
import kotlinx.coroutines.flow.Flow

interface TodosDatabaseRepository {
    suspend fun getAllTodos(): Flow<List<TodosModel>>
    suspend fun insertTodo(todo: TodosModel): Flow<Long>
    suspend fun getTodoByTitle(query: String): Flow<List<TodosModel>>
}