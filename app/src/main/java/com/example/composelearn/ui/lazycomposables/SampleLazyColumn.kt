package com.example.composelearn.ui.lazycomposables

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun SampleLazyColumn() {
    val list = (1..100).toList()
    val state = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val scrollToTop by remember {
        derivedStateOf {
            state.firstVisibleItemIndex > 0
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = state,
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Text(text = "Lazy Column")
            }
            itemsIndexed(list) { _, item ->
                Text(
                    text = item.toString(),
                    modifier = Modifier
                        .background(color = Color.Green)
                        .padding(10.dp)
                        .fillMaxWidth()
                        .animateItemPlacement(tween(300))
                )
            }
            item {
                Text(text = "Last Position")
            }
        }
        if (scrollToTop) {
            Box(contentAlignment = Alignment.BottomEnd, modifier = Modifier.fillMaxSize()) {
                OutlinedButton(onClick = {
                    scope.launch {
                        state.animateScrollToItem(0)
                    }
                }) {
                    Text(text = "Scroll to Top")
                }
            }
        }
    }
}

object TopWithFooter : Arrangement.Vertical {
    override fun Density.arrange(totalSize: Int, sizes: IntArray, outPositions: IntArray) {

        var y = 0

        sizes.forEachIndexed { index, size ->
            outPositions[index] = y
            y += size
        }
        if (y < totalSize) {
            val lastIndex =
                outPositions.lastIndex
            outPositions[lastIndex] = totalSize - sizes.last()
        }
    }
}

