package com.example.composelearn.musicapp.musicappUi.favouritescreenui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composelearn.R
import com.example.composelearn.musicapp.musicappUi.settingsmoduleui.CustomMusicAppBar
import com.example.composelearn.musicapp.utils.CoilApiImageView
import com.example.composelearn.theme.ComposeLearnTheme
import com.example.composelearn.theme.Orange


@Composable
fun FavouriteScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        CustomMusicAppBar(leftIcon = R.drawable.baseline_library_music_24, title = "Favourites") {


        }
        Row(modifier = Modifier.padding(horizontal = 15.dp)) {
            Button(
                onClick = { },
                modifier = Modifier
                    .weight(1f)
                    .clip(shape = RoundedCornerShape(40.dp)),
                colors = ButtonDefaults.buttonColors(backgroundColor = Orange),
                contentPadding = PaddingValues(vertical = 15.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.shuffle_icon),
                    contentDescription = "shuffle icon",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                Text(text = "Shuffle")
            }
            Spacer(modifier = Modifier.size(10.dp))
            Button(
                onClick = { },
                modifier = Modifier
                    .weight(1f)
                    .clip(shape = RoundedCornerShape(40.dp)),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
                contentPadding = PaddingValues(vertical = 15.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .background(color = Orange, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Play Icon",
                        tint = Color.White, modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                }
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                Text(text = "Play", color = Orange)
            }
        }

        FavouriteList()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun FavouriteScreenPreview() {
    ComposeLearnTheme {
        FavouriteScreen()
    }
}

@Composable
fun FavouriteList() {
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
                        text = "170 Favourites",
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
            FavouriteView()
        }
    }
}

@Composable
fun FavouriteView(imageUrl: String = "https://picsum.photos/id/110/800/800") {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 5.dp)
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
            Text(text = "1 album", maxLines = 1, overflow = TextOverflow.Ellipsis)
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
fun FavouritePreview() {
    FavouriteView()
}
