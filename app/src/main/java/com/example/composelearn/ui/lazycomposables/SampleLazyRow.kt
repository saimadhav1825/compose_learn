package com.example.composelearn.ui.lazycomposables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true, name = "default")
@Composable
fun SampleLazyRow() {
    val list = (1..100).toList()
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Lazy Row")
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(
            contentPadding = PaddingValues(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(list) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color.Blue , shape = RoundedCornerShape(10.dp)), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "$it", color = Color.White
                    )
                }
            }
        }
    }

}
