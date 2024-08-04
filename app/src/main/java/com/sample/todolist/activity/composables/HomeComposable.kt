package com.sample.todolist.activity.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.sample.todolist.R
import com.sample.todolist.activity.viewmodels.HomeViewModel
import com.sample.todolist.navigation.NavigationManager
import com.sample.todolist.navigation.Routes

/// Home Composable
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeComposable(viewModel: HomeViewModel) {
    var search by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val allTodos by viewModel.searchTodosResults.collectAsState()
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = stringResource(id = R.string.home_title)) })
    }, floatingActionButton = {
        FloatingActionButton(
            onClick = {
                NavigationManager.NavController.navigate(Routes.ADD_TODO)
            },
        ) {
            Icon(Icons.Default.Add, contentDescription = stringResource(id = R.string.add_todo))
        }
    }) { padding ->
        Box(modifier = Modifier.padding(paddingValues = padding)) {
            Column(
                modifier = Modifier
                    .padding(all = 16.dp)
                    .fillMaxSize()
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = search,
                    onValueChange = {
                        search = it
                        viewModel.searchTodo(search)
                    },
                    colors = OutlinedTextFieldDefaults.colors(),
                    minLines = 1,
                    maxLines = 1,
                    singleLine = true,
                    label = {
                        Text(text = stringResource(id = R.string.search_for_todo))
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    )
                )
                if (allTodos.isEmpty())
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally)
                            .fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        Text(text = stringResource(id = R.string.click_to_add_new_todo))
                    }
                else
                    LazyColumn {
                        items(allTodos) {
                            Text(text = it.title)
                        }
                    }
            }
        }
    }

}