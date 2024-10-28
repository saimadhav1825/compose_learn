package com.example.composelearn.musicapp.musicappUi.homescreenui.homescreencomoponents

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun SongsScreen() {
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
                        text = "560 Songs",
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
            SingleSongView {

            }
        }
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SongsScreenPreview() {
    ComposeLearnTheme {
        SongsScreen ()
    }
}


@Composable
fun SingleSongView(
    imageUrl: String = "https://picsum.photos/id/110/800/800",
    isPlay: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 5.dp).clickable {
                isPlay.invoke()
            }
    ) {
        CoilApiImageView(
            imageUrl = imageUrl, modifier = Modifier
                .size(65.dp)
                .background(
                    color = Color.Gray.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(horizontal = 10.dp), contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 10.dp)
        ) {
            Text(
                text = "Song Title",
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Song Album", maxLines = 1, overflow = TextOverflow.Ellipsis)
                Spacer(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .width(1.dp)
                        .height(15.dp)
                        .background(color = Color.Gray)

                )
                Text(text = "Song Time", maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
        Box(
            modifier = Modifier
                .size(25.dp)
                .background(color = Orange, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Play Icon",
                tint = Color.White, modifier = Modifier.padding(2.dp)
            )
        }
        IconButton(onClick = { isPlay.invoke() }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More Icon",
                modifier = Modifier.padding(start = 5.dp)
            )
        }
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SingleSongPreview() {
    SingleSongView {

    }
}

