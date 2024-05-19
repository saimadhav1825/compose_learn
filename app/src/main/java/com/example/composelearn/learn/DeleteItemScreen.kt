package com.example.composelearn.learn

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Preview
@Composable
fun DeleteItemScreen() {
    val deleteItemViewModel = hiltViewModel<DeleteItemViewModel>()
    val uiState by deleteItemViewModel.deleteState.collectAsState()
    Scaffold(floatingActionButton = {
        IconButton(onClick = {
            deleteItemViewModel.handleDeleteEvent(DeleteStateEvent.AddItem("${uiState.list.size + 1}"))
        }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
        }
    }) {
        Box(modifier = Modifier.padding(it)) {
            LazyColumn {
                items(uiState.list) {
                    Row {
                        Text(text = it)
                        IconButton(onClick = {
                            deleteItemViewModel.handleDeleteEvent(DeleteStateEvent.DeleteItem(it))
                        }) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "Add")
                        }
                    }
                }
            }
        }
    }

}