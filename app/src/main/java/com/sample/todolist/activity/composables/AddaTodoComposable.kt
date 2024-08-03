package com.sample.todolist.activity.composables

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.sample.todolist.navigation.NavigationManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddaTodoComposable() {
    var todo by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Add a Todo") }, navigationIcon = {
                IconButton(onClick = {
                    NavigationManager.NavController.popBackStack()
                }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            })
        },
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {

            Column(modifier = Modifier.padding(paddingValues = PaddingValues(all = 16.dp))) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = todo,
                    onValueChange = {
                        todo = it
                    },
                    label = {
                        Text(text = "Add TODO")
                    },
                    colors = OutlinedTextFieldDefaults.colors(),
                    minLines = 1,
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                ElevatedButton(modifier = Modifier.align(alignment = Alignment.CenterHorizontally), onClick = {
                    focusManager.clearFocus()
                    Toast.makeText(context, "Todo Added", Toast.LENGTH_SHORT).show()
                    NavigationManager.NavController.popBackStack()
                }) {
                    Text(text = "Add TODO")
                }
            }
        }
    }
}