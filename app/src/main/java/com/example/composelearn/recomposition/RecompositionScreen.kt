package com.example.composelearn.recomposition

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RecompositionScreen() {
    RecompositionScreenContent()
}

@Composable
fun RecompositionScreenContent() {
    Column {
        var count by rememberSaveable {
            mutableIntStateOf(0)
        }

        Button(onClick = {
            count++
        }) {
            Text(text = "Count")

        }
        Text(text = count.toString())
    }
}


@Preview
    (showBackground = true)
@Composable
fun RecompositionScreenPreview() {
    RecompositionScreenContent()
}