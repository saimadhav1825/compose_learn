package com.example.composelearn.ui.lazycomposables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp

@Preview(name = "default")
@Composable
fun SampleLazyVerticalGrid() {
    val list = (1..100).toList()
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "LazyVerticalGrid")
        Spacer(modifier = Modifier.height(10.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(list) {
                Box(
                    modifier = Modifier
                        .background(Color.Blue, shape = RoundedCornerShape(10.dp))
                        .height(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "$it", color = Color.White
                    )
                }
            }
        }
    }
}

@Preview(name = "default")
@Composable
fun SampleCustomLazyVerticalGrid() {
    val list = (1..100).toList()
    LazyVerticalGrid(
        columns = object : GridCells {
            override fun Density.calculateCrossAxisCellSizes(
                availableSize: Int,
                spacing: Int
            ): List<Int> {
                val firstColumn = (availableSize - spacing) * 2 / 3
                val secondColumn =
                    availableSize - spacing - firstColumn
                return listOf(firstColumn, secondColumn)
            }
        },
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(list) {
            Box(
                modifier = Modifier
                    .background(Color.Blue, shape = RoundedCornerShape(10.dp))
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$it", color = Color.White
                )
            }
        }
    }
}

@Preview(name = "default")
@Composable
fun SampleCustomLazyVerticalGridSpan() {
    val list = (1..100).toList()
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        list.forEachIndexed { index, i ->
            if (index == 0) {
                item(span = {
                    GridItemSpan(maxLineSpan)
                }) {
                    Box(
                        modifier = Modifier
                            .background(Color.Blue, shape = RoundedCornerShape(10.dp))
                            .height(100.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$i", color = Color.White
                        )
                    }
                }
            } else {
                item(span = {
                    GridItemSpan(1)
                }) {
                    Box(
                        modifier = Modifier
                            .background(Color.Blue, shape = RoundedCornerShape(10.dp))
                            .height(100.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$i", color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Preview(name = "default")
@Composable
fun SampleCustomLazyVerticalGridSpanWithCell() {
    val list = (1..100).toList()
    LazyVerticalGrid(
        columns = object : GridCells {
            override fun Density.calculateCrossAxisCellSizes(
                availableSize: Int,
                spacing: Int
            ): List<Int> {
                val firstColumn = (availableSize - spacing) * 2 / 3
                val secondColumn = availableSize - spacing - firstColumn
                return listOf(firstColumn, secondColumn)
            }
        },
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        list.forEachIndexed { index, i ->
            if (index == 0) {
                item(span = {
                    GridItemSpan(maxLineSpan)
                }) {
                    Box(
                        modifier = Modifier
                            .background(Color.Blue, shape = RoundedCornerShape(10.dp))
                            .height(100.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$i", color = Color.White
                        )
                    }
                }
            } else {
                item(span = {
                    GridItemSpan(1)
                }) {
                    Box(
                        modifier = Modifier
                            .background(Color.Blue, shape = RoundedCornerShape(10.dp))
                            .height(100.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$i", color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Preview(name = "default")
@Composable
fun SampleCustomLazyVerticalGridSpanIndexWithCell() {
    val list = (1..100).toList()
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        list.forEachIndexed { index, i ->
            if (index % 7 == 0) {
                item(span = {
                    GridItemSpan(maxLineSpan)
                }) {
                    Box(
                        modifier = Modifier
                            .background(Color.Blue, shape = RoundedCornerShape(10.dp))
                            .height(100.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$i", color = Color.White
                        )
                    }
                }
            } else {
                item(span = {
                    GridItemSpan(1)
                }) {
                    Box(
                        modifier = Modifier
                            .background(Color.Blue, shape = RoundedCornerShape(10.dp))
                            .height(100.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$i", color = Color.White
                        )
                    }
                }
            }
        }
    }
}