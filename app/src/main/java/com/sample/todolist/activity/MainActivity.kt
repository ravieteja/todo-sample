package com.sample.todolist.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.sample.todolist.activity.composables.DefaultUI
import com.sample.todolist.activity.viewmodels.HomeViewModel
import com.sample.todolist.ui.theme.TodoListTheme

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