package com.example.composelearn.musicapp.musicappUi.recentplayedui

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composelearn.musicapp.musicappUi.favouritescreenui.FavouriteView
import com.example.composelearn.musicapp.musicappUi.playlistscreenui.SearchField
import com.example.composelearn.theme.Orange

@Composable
fun RecentSearchScreen(onBack: () -> Unit = {}) {
    val list = List(20) {
        it
    }
    var searchedText by rememberSaveable {
        mutableStateOf("")
    }
    val typeSearchlist = listOf(
        "Songs", "Artist", "Album", "Folders"
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp)
    ) {

        Spacer(modifier = Modifier.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { onBack.invoke() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow back")
            }
            Spacer(modifier = Modifier.width(10.dp))
            SearchField(modifier = Modifier) {
                searchedText = it
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        if (searchedText.isNotEmpty()) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(typeSearchlist) {
                    Box(
                        modifier = Modifier
                            .border(
                                border = BorderStroke(1.dp, color = Orange),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .width(80.dp), contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = it,
                            modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                            color = Orange
                        )
                    }
                }
            }
        }
        if (searchedText.isEmpty()) {
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Recent Searches", style = MaterialTheme.typography.h6)
                Text(text = "Clear All", color = Orange)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(0.5.dp)
                    .background(color = Color.Gray.copy(alpha = 0.5f))
            )
            Spacer(modifier = Modifier.height(10.dp))

            val recentSearchList = List(20) {
                it
            }
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(recentSearchList) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 5.dp)
                    ) {
                        Text(
                            text = "Song Title",
                            style = MaterialTheme.typography.subtitle1,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f),
                            color = Color.Gray.copy(alpha = 0.7f)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear Icon",
                            tint = Color.Gray.copy(alpha = 0.7f)
                        )
                    }
                }
            }
        }
        if (searchedText.isNotEmpty()) {
            LazyColumn {
                items(list) {
                    FavouriteView()
                }
            }
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun RecentSearchPreview() {
    RecentSearchScreen()
}