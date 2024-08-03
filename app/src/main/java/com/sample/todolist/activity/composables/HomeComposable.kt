package com.sample.todolist.activity.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.sample.todolist.navigation.NavigationManager
import com.sample.todolist.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeComposable() {
    var search by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Home") })
    }, floatingActionButton = {
        FloatingActionButton(
            onClick = {
                NavigationManager.NavController.navigate(Routes.ADD_TODO)
            },
        ) {
            Icon(Icons.Default.Add, contentDescription = "")
        }
    }) { padding ->
        Box(modifier = Modifier.padding(paddingValues = padding)) {
            Column(modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxSize()) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = search,
                    onValueChange = {
                        search = it
                    },
                    colors = OutlinedTextFieldDefaults.colors(),
                    minLines = 1,
                    maxLines = 1,
                    singleLine = true,
                    label = {
                        Text(text = "Search for TODO")
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    )
                )
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(text = "Press the + button to add a TODO item")
                }
            }
        }
    }

}