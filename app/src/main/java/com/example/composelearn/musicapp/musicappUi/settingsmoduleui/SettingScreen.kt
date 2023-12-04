package com.example.composelearn.musicapp.musicappUi.settingsmoduleui

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composelearn.R
import com.example.composelearn.musicapp.musicappnavigation.musicappgraph.settinggraph.SettingRoute
import com.example.composelearn.musicapp.musicappviewmodels.SettingsViewModel
import com.example.composelearn.theme.ComposeLearnTheme
import com.example.composelearn.theme.Orange

@Composable
fun SettingScreen(onNavigate: (String) -> Unit = {}) {
    val settingsViewModel = hiltViewModel<SettingsViewModel>()
    Column(modifier = Modifier.fillMaxSize()) {
        CustomMusicAppBar(
            leftIcon = R.drawable.baseline_library_music_24,
            title = "Settings",
            secondIcon = R.drawable.message_icon
        ) {

        }
        Box(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .fillMaxWidth()
                .height(130.dp)
                .background(color = Orange, shape = RoundedCornerShape(20.dp))
        ) {
            Row(modifier = Modifier.padding(10.dp)) {
                Column(modifier = Modifier.weight(0.6f)) {
                    Text(
                        text = "Enjoy All Benefits",
                        style = MaterialTheme.typography.h6,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Enjoy Listening Songs with Better Audi Quality,Without Restrictions and without ads",
                        fontSize = 10.sp, color = Color.White, fontWeight = FontWeight(300)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Box(
                        modifier = Modifier
                            .background(color = Color.White, shape = RoundedCornerShape(20.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Get Premium",
                            color = Orange,
                            fontSize = 10.sp,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                        )
                    }

                }
                Spacer(modifier = Modifier.width(5.dp))
                Image(
                    painter = painterResource(id = R.drawable.peakpx),
                    contentDescription = "LeftIconClick",
                    modifier = Modifier
                        .weight(0.4f)
                        .fillMaxHeight()
                        .background(color = Orange)
                        .clip(
                            RoundedCornerShape(10.dp)
                        ),
                    contentScale = ContentScale.FillHeight
                )
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(0.5.dp)
                .background(color = MaterialTheme.colors.primary.copy(alpha = 0.5f))
        )
        var isDarkMode by rememberSaveable {
            mutableStateOf(false)
        }
        LazyColumn(contentPadding = PaddingValues(horizontal = 15.dp)) {
            itemsIndexed(settingsViewModel.uiState.value.settingList) { index, item ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = item.icon),
                        contentDescription = "icon",
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
                    )
                    Text(text = item.title, modifier = Modifier.weight(1f))
                    if (item.isCheckBox) {
                        Switch(checked = isDarkMode, onCheckedChange = {
                            isDarkMode = !isDarkMode
                        })
                    } else {
                        IconButton(onClick = {
                            if (index == 1) onNavigate.invoke(SettingRoute.NotificationScreen.name)
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = "Forward Icon"
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CustomMusicAppBar(
    leftIcon: Int = R.drawable.back_icon,
    leftIconColor: Color = MaterialTheme.colors.primary,
    leftIconClick: () -> Unit = {},
    title: String = "",
    rightFirstIconVisible: Boolean = false,
    rightFirstIcon: Int = R.drawable.search_icon,
    rightFirstClick: () -> Unit = {},
    secondIcon: Int = R.drawable.search_icon,
    secondIconClick: () -> Unit = {}
) {
    Surface(modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colors.background) {

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { leftIconClick.invoke() }) {
                Image(
                    painter = painterResource(id = leftIcon),
                    contentDescription = "LeftIconClick",
                    colorFilter = ColorFilter.tint(leftIconColor)
                )
            }

            Text(
                text = title,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.weight(1f)
            )
            if (rightFirstIconVisible) {
                IconButton(onClick = { rightFirstClick.invoke() }) {
                    Image(
                        painter = painterResource(id = rightFirstIcon),
                        contentDescription = "rightFirstClick",
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
                    )
                }
            }
            IconButton(onClick = {
                secondIconClick.invoke()
            }) {
                Image(
                    painter = painterResource(id = secondIcon),
                    contentDescription = "secondIconClick",
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
                )
            }
        }
    }
}


@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO, widthDp = 320)
@Preview(name = "dark theme", showBackground = true, uiMode = UI_MODE_NIGHT_YES, widthDp = 320)
@Composable
fun SettingScreenPreview() {
    ComposeLearnTheme {
        SettingScreen {

        }
    }

}