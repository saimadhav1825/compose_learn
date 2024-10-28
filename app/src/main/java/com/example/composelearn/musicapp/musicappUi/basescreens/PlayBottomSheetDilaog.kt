package com.example.composelearn.musicapp.musicappUi.basescreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlayBottomSheetDialog() {
    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Expanded,)
    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetContent = {
            Scaffold {
                Column {
                    Text(text = "Song Name")
                    Text(text = "Song Name")
                    Text(text = "Song Name")
                    Text(text = "Song Name")
                    Text(text = "Song Name")
                    Text(text = "Song Name")
                    Text(text = "Song Name")
                    Text(text = "Song Name")
                    Text(text = "Song Name")
                    Text(text = "Song Name")
                    Text(text = "Song Name")
                    Text(text = "Song Name")
                    Text(text = "Song Name")
                    Text(text = "Song Name")
                    Text(text = "Song Name")
                    Text(text = "Song Name")
                    Text(text = "Song Name")
                    Text(text = "Song Name")
                }
            }

        }) {


    }
}