package com.example.composelearn.musicapp.musicappUi.homescreenui.homescreencomoponents

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
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

@Composable
fun AlbumScreen() {
    val list = List(20) {
        it
    }
    Column(modifier = Modifier.fillMaxSize()) {
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

        LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = PaddingValues(10.dp)) {
            items(list) {
                AlbumView()
            }
        }
    }
}

@Composable
fun AlbumView(imageUrl: String = "https://picsum.photos/id/200/1200/1200") {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 5.dp)
    ) {
        CoilApiImageView(
            imageUrl = imageUrl, modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(
                    color = Color.Gray.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(20.dp)
                )
                .clip(RoundedCornerShape(20.dp))
                .padding(horizontal = 10.dp), contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Artist Name",
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More Icon",
                modifier = Modifier.padding(start = 5.dp)
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "1 album", maxLines = 1, overflow = TextOverflow.Ellipsis)
            Spacer(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .width(1.dp)
                    .height(15.dp)
                    .background(color = Color.Gray)

            )
            Text(text = "Year", maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
        Text(text = "1 album", maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AlbumPreview() {
    AlbumView()
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AlbumScreenPreview() {
    ComposeLearnTheme {
        AlbumScreen()
    }
}

