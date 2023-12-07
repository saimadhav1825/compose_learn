package com.example.composelearn.musicapp.musicappUi.homescreenui

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composelearn.R
import com.example.composelearn.musicapp.musicappUi.settingsmoduleui.CustomMusicAppBar
import com.example.composelearn.theme.ComposeLearnTheme

@Composable
fun HomeScreen(onNavigation: (String) -> Unit={}) {
    Column(modifier = Modifier.fillMaxSize()) {
        CustomMusicAppBar(leftIcon = R.drawable.baseline_library_music_24, title = "Music App") {

        }

        CustomTabLayout(onNavigation)
    }
}


@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
    ComposeLearnTheme {
        HomeScreen()
    }
}