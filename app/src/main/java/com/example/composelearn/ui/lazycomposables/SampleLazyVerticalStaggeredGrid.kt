package com.example.composelearn.ui.lazycomposables

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices.AUTOMOTIVE_1024p
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.random.Random


@Preview(showBackground = true)
@Composable
fun SampleLazyVerticalStaggeredGridTwo() {
    val list = List(100) {
        ListItem(
            height = Random.nextInt(100, 300).dp,
            color = Color(
                Random.nextLong(0xFFFFFFFF)
            ).copy(1f)
        )
    }
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(3),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp), verticalItemSpacing = 10.dp
    ) {
        items(list) {
            Box(
                modifier = Modifier
                    .background(it.color, shape = RoundedCornerShape(10.dp))
                    .height(it.height)
                    .fillMaxWidth(),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SampleLazyVerticalStaggeredGridThree() {
    val list = List(100) {
        ListItem(
            height = Random.nextInt(100, 300).dp,
            color = Color(
                Random.nextLong(0xFFFFFFFF)
            ).copy(1f)
        )
    }
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp), verticalItemSpacing = 10.dp
    ) {
        items(list) {
            Box(
                modifier = Modifier
                    .background(it.color, shape = RoundedCornerShape(10.dp))
                    .height(it.height)
                    .fillMaxWidth(),
            )
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO, device = AUTOMOTIVE_1024p)
@Composable
fun SampleLazyVerticalStaggeredGridAdaptive() {
    val list = List(100) {
        ListItem(
            height = Random.nextInt(100, 300).dp,
            color = Color(
                Random.nextLong(0xFFFFFFFF)
            ).copy(1f)
        )
    }
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(100.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp), verticalItemSpacing = 10.dp
    ) {
        items(list) {
            Box(
                modifier = Modifier
                    .background(it.color, shape = RoundedCornerShape(10.dp))
                    .height(it.height)
                    .fillMaxWidth(),
            )
        }
    }
}
data class ListItem(
    val height: Dp,
    val color: Color
)