package com.example.composelearn.ui.drawcomposables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showBackground = true, widthDp = 320)
@Composable
fun SampleDrawExamples() {
    Canvas(modifier = Modifier.fillMaxSize(), onDraw = {
        drawCircle(
            color = Color.Green,
            radius = 60.dp.toPx(),
            center = Offset(
                x = 100.dp.toPx(), y = 100.dp.toPx()
            ),
        )
    })

}
@Preview(showBackground = true)
@Composable
fun SampleDrawScaleExamples() {
    Canvas(modifier = Modifier.fillMaxSize()){
        scale(scaleX = 10f, scaleY = 15f){
            drawCircle(
                color = Color.Green,
                radius = 15.dp.toPx(),
            )
        }
    }
}