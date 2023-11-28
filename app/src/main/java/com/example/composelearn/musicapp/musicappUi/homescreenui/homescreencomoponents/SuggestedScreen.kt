package com.example.composelearn.musicapp.musicappUi.homescreenui.homescreencomoponents

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composelearn.musicapp.utils.CoilApiImageView
import com.example.composelearn.theme.Orange

@Composable
fun SuggestedScreen() {
    val recentList = List(10) {
        "Song Id $it"
    }
    LazyColumn {
        item {
            TitleSeeAllView(title = "Recent Played") {

            }
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(recentList) {
                    SongItemPreview(title = it)
                }
            }
        }
        item {
            TitleSeeAllView(title = "Artists") {

            }
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(recentList) {
                    CircularSongItemPreview(title = it)
                }
            }
        }
        item {
            TitleSeeAllView(title = "Most Player") {

            }
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(recentList) {
                    SongItemPreview(title = it)
                }
            }
        }
    }
}

@Composable
fun TitleSeeAllView(modifier: Modifier = Modifier, title: String, onSeeAllClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .then(modifier)
    ) {
        Text(text = title, style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f))
        TextButton(onClick = { onSeeAllClick.invoke() }) {
            Text(text = "See All", color = Orange)
        }
    }
}


@Composable
fun SongItemPreview(title: String, imageUrl: String = "https://picsum.photos/id/103/800/800") {
    Column {
        CoilApiImageView(
            imageUrl = imageUrl,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(20.dp))
        )
        Text(text = title, style = MaterialTheme.typography.h6)
    }
}

@Composable
fun CircularSongItemPreview(
    title: String,
    imageUrl: String = "https://picsum.photos/id/103/800/800"
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CoilApiImageView(
            imageUrl = imageUrl,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun TitleSeeAllPreview() {
    TitleSeeAllView(title = "Recent Played") {

    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SuggestScreenPreview() {
    SuggestedScreen()
}

