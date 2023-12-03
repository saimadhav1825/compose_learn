package com.example.composelearn.musicapp.musicappUi.homescreenui.homescreencomoponents

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composelearn.musicapp.utils.CoilApiImageView
import com.example.composelearn.theme.ComposeLearnTheme
import com.example.composelearn.theme.Orange

@Composable
fun ArtistScreen() {
    val list = List(20) {
        it
    }
    LazyColumn(modifier = Modifier.fillMaxSize()) {

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 10.dp)
            ) {
                Row {
                    Text(
                        text = "85 Artists",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold, color = Color.Black
                    )
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(color = Color.Gray.copy(alpha = 0.5f))
                )
            }

        }
        items(list) {
            ArtistView()
        }
    }
}


@Composable
fun ArtistView(imageUrl: String = "https://picsum.photos/id/200/1000/1000") {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 5.dp)
    ) {
        CoilApiImageView(
            imageUrl = imageUrl, modifier = Modifier
                .size(70.dp)
                .background(
                    color = Color.Gray.copy(alpha = 0.5f),
                    shape = CircleShape
                )
                .clip(CircleShape)
                .padding(horizontal = 10.dp), contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 10.dp)
        ) {
            Text(
                text = "Artist Name",
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "1 album", maxLines = 1, overflow = TextOverflow.Ellipsis)
                Spacer(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .width(1.dp)
                        .height(15.dp)
                        .background(color = Color.Gray)

                )
                Text(text = "10 songs", maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "More Icon",
            modifier = Modifier.padding(start = 5.dp)
        )
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ArtistSongPreview() {
    ArtistView()
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ArtistsScreenPreview() {
    ComposeLearnTheme {
        ArtistScreen()
    }
}

