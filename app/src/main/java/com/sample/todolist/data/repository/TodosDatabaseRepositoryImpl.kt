package com.sample.todolist.data.repository

import android.app.Application
import com.sample.todolist.data.database.TodosDatabase
import com.sample.todolist.data.model.TodosModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TodosDatabaseRepositoryImpl(app: Application) : TodosDatabaseRepository {
    private var todosDb: TodosDatabase = TodosDatabase.init(app.applicationContext)

    /// returns list of saved todos
    override suspend fun getAllTodos(): Flow<List<TodosModel>> {
        return flow {
            emit(todosDb.todosDao().getAllTodos())
        }
    }

    /// insert new todo
    override suspend fun insertTodo(todo: TodosModel): Flow<Long> {
        return flow {
            emit(todosDb.todosDao().insertTodo(todo))
        }
    }

    /// returns list of todos based query
    override suspend fun getTodoByTitle(query: String): Flow<List<TodosModel>> {
        return flow {
            emit(todosDb.todosDao().getTodosByTitle(query))
        }
    }

}