package com.example.composelearn.musicapp.musicappUi.recentplayedui

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composelearn.R
import com.example.composelearn.musicapp.musicappUi.favouritescreenui.FavouriteView
import com.example.composelearn.musicapp.musicappUi.settingsmoduleui.CustomMusicAppBar
import com.example.composelearn.musicapp.musicappnavigation.musicappgraph.homegraph.HomeRoute


@Composable
fun RecentPlayedScreen(onBack: () -> Unit = {}, onNavigate: (String) -> Unit = {}) {

    val list = List(20) {
        it
    }
    Column(modifier = Modifier.fillMaxSize()) {
        CustomMusicAppBar(
            leftIcon = R.drawable.back_icon,
            title = "RecentPlayed",
            rightFirstIcon = R.drawable.search_icon,
            secondIcon = R.drawable.message_icon,
            rightFirstIconVisible = true,
            leftIconClick = onBack, rightFirstClick = {
                onNavigate.invoke(HomeRoute.RecentSearchScreen.route)
            }
        )
        LazyColumn{
            items(list){
                FavouriteView()
            }
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun RecentPlayerPreview() {
    RecentPlayedScreen()
}