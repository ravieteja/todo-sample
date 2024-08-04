@file:OptIn(FlowPreview::class)

package com.sample.todolist.activity.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.todolist.R
import com.sample.todolist.data.model.TodosModel
import com.sample.todolist.data.repository.TodosDatabaseRepository
import com.sample.todolist.data.repository.TodosDatabaseRepositoryImpl
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

/// ViewModel for Home Activity
class HomeViewModel(private val app: Application) : AndroidViewModel(app) {
    private val repository = TodosDatabaseRepositoryImpl(app)

    private val _allTodos = MutableStateFlow<List<TodosModel>>(emptyList())
//    val allTodos: StateFlow<List<TodosModel>> = _allTodos

    private val _searchTodosResults = MutableStateFlow<List<TodosModel>>(emptyList())
    val searchTodosResults: StateFlow<List<TodosModel>> = _searchTodosResults

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _searchQuery = MutableStateFlow("")
    private val searchQuery: StateFlow<String> = _searchQuery

    init {
        getAllTodos()
        searchTodo()
    }

    /// Get all todos from database
    private fun getAllTodos() = viewModelScope.launch {
        repository.getAllTodos().collect {
            _allTodos.value = it
            _searchTodosResults.value = _allTodos.value
        }
    }

    /// Insert to-do to database
    fun insertTodo(todo: TodosModel, onSuccess: () -> Unit, onError: (String) -> Unit) = viewModelScope.launch {
        // check if title is "error"
        if (todo.title.lowercase() == "error") {
            onError(app.getString(R.string.failed_to_add_todo))
            return@launch
        }

        _isLoading.value = true
        try {
            // delay of 3 sec
            delay(3000)
            // insert to-do
            repository.insertTodo(todo).collect {
                if (it != -1L) {
                    onSuccess()
                    getAllTodos()
                } else {
                    onError(app.getString(R.string.failed_to_add_todo))
                }
            }
        } catch (e: Exception) {
            // show error on exception
            onError(app.getString(R.string.failed_to_add_todo))
        } finally {
            _isLoading.value = false
        }
    }

    /// search query entered
    fun searchTodo(query: String) {
        _searchQuery.value = query
    }

    /// search to-do from database
    private fun searchTodo() = viewModelScope.launch {
        viewModelScope.launch {
            // delay of 2 sec
            searchQuery.debounce(2000).collect {
                // search for title with query
                repository.getTodoByTitle(it).collect { todos ->
                    _searchTodosResults.value = todos
                }
            }
        }

    }
}