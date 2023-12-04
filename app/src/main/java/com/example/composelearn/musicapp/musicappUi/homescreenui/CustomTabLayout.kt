package com.example.composelearn.musicapp.musicappUi.homescreenui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composelearn.musicapp.musicappUi.homescreenui.homescreencomoponents.AlbumScreen
import com.example.composelearn.musicapp.musicappUi.homescreenui.homescreencomoponents.ArtistScreen
import com.example.composelearn.musicapp.musicappUi.homescreenui.homescreencomoponents.FoldersScreen
import com.example.composelearn.musicapp.musicappUi.homescreenui.homescreencomoponents.SongsScreen
import com.example.composelearn.musicapp.musicappUi.homescreenui.homescreencomoponents.SuggestedScreen
import com.example.composelearn.theme.Orange
import kotlinx.coroutines.launch

data class TabItem(
    val title: String,
    val screen: @Composable () -> Unit,
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomTabLayout(onNavigation: (String) -> Unit) {
    val tabItems = listOf(
        TabItem(
            title = "Suggested",
            screen = {
                SuggestedScreen(onNavigation)
            },
        ),
        TabItem(
            title = "Songs",
            screen = {
                SongsScreen()
            },
        ),
        TabItem(
            title = "Artists",
            screen = {
                ArtistScreen()
            },
        ), TabItem(
            title = "Album",
            screen = {
                AlbumScreen()
            },
        ), TabItem(
            title = "Folders",
            screen = {
                FoldersScreen()
            },
        )

    )
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0) {
        tabItems.size
    }
    LazyRow(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
    ) {
        items(tabItems.size) { index ->
            // Calculate the underline width based on the current tab index
            val underlineWidth = (700 / tabItems.size).dp
            val isSelected = pagerState.currentPage == index

            Column {
                // Tab composable
                Tab(
                    selected = isSelected, onClick = {
                        if (!isSelected) {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    }, text = {
                        Text(text = tabItems[index].title)
                    }
                )
                // Spacer to position the underline below the tabs
                Spacer(
                    modifier = Modifier
                        .height(3.dp)
                        .width(underlineWidth)
                        .background(if (isSelected) Orange else Color.Gray)
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }

// Set the background color of the HorizontalPager to transparent
    HorizontalPager(
        state = pagerState,
        // Set the background color of the pager content to transparent
        modifier = Modifier.background(Color.Transparent)
    ) { page ->
        // Wrap the content of each tab with rememberUpdatedState
        val tabContent = rememberUpdatedState(tabItems[page].screen)
        tabContent.value()
    }
}

@Preview(showBackground = true)
@Composable
fun CustomTabView() {
    CustomTabLayout {

    }
}