package com.sample.todolist.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.sample.todolist.activity.composables.DefaultUI
import com.sample.todolist.activity.viewmodels.HomeViewModel
import com.sample.todolist.data.database.TodosDatabase
import com.sample.todolist.data.repository.TodosDatabaseRepositoryImpl
import com.sample.todolist.ui.theme.TodoListTheme
import com.sample.todolist.utils.factory.ViewModelFactory

class MainActivity : ComponentActivity() {

    /*private val homeViewModel: HomeViewModel by viewModels {
        ViewModelFactory(HomeViewModel::class.java) {
            val application = application
            TodosDatabase.init(application.applicationContext)
            val repository = TodosDatabaseRepositoryImpl(app = application)
            HomeViewModel(application, repository)
        }
    }*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            TodoListTheme {
                Surface {
                    DefaultUI(homeViewModel)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoListTheme {

    }
}