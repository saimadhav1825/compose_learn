package com.example.composelearn

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun LazyColumnExample() {

    val list = remember {
        (1..100).toMutableList()
    }
    Column {
        Button(onClick = {
            list.add(0, list.size + 1)
        }) {

        }
        LazyColumn {
            items(list) {
                println("Recompose")
                Text(text = it.toString())
            }
        }
    }
}

