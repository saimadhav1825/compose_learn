package com.example.composelearn.musicapp.musicappUi.playlistscreenui

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composelearn.theme.Orange

@Composable
fun SearchField(modifier: Modifier, onSearchText: (String) -> Unit) {
    var search by rememberSaveable {
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .then(modifier),
        value = search,
        onValueChange = {
            search = it
            onSearchText.invoke(it)
        }, leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
        }, trailingIcon = {
            if (search.isNotEmpty()) {
                IconButton(onClick = {
                    search = ""
                    onSearchText.invoke("")
                }) {
                    Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear")
                }
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color.Gray.copy(0.5f),
            unfocusedBorderColor = Color.Transparent,
            errorBorderColor = Orange, errorLeadingIconColor = Orange
        ), isError = false,
        shape = RoundedCornerShape(20.dp),
        singleLine = true
    )
}

@Preview(showBackground = true, widthDp = 320, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun SearchFiledPreview() {
    SearchField(modifier = Modifier) {

    }
}