package com.sample.todolist.activity.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sample.todolist.navigation.NavigationManager
import com.sample.todolist.navigation.Routes

@Composable
fun DefaultUI() {
    val navigationController = rememberNavController()

    NavigationManager.init(navigationController)

    Column(Modifier.fillMaxSize()) {
        NavHost(
            navController = NavigationManager.NavController,
            startDestination = Routes.HOME
        ) {
            composable(Routes.HOME) {
                HomeComposable()
            }
            composable(Routes.ADD_TODO) {
                AddaTodoComposable()
            }
        }
    }
}