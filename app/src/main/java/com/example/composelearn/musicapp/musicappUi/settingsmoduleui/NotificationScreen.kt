package com.example.composelearn.musicapp.musicappUi.settingsmoduleui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composelearn.musicapp.musicappUi.basescreens.PlayBottomSheetDialog

data class Notification(
    val title: String,
    val value: String
)

val notificationList =
    listOf(
        Notification(
            title = "New Music", "push"
        ),
        Notification(
            title = "PlayList Updates", "push,email"
        ),
        Notification(
            title = "Artists Updates", "push,email"
        ),
        Notification(
            title = "App Offers", "push,email"
        ),
        Notification(
            title = "Payment & Subscriptions", "push,emial"
        ),
        Notification(
            title = "New Features", "push"
        ),
        Notification(
            title = "Security", "push"
        )
    )

@Composable
fun NotificationScreen(onBack: () -> Unit = {}) {
    var isPlay by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { onBack.invoke() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Go Back")
            }
            Text(text = "Notification")
        }
        LazyColumn(
            contentPadding = PaddingValues(15.dp), verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(notificationList) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = it.title, style = MaterialTheme.typography.h6)
                        Text(text = it.value)
                    }
                    IconButton(onClick = {
                        isPlay = !isPlay
                    }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "arrow right"
                        )
                    }
                }
            }
        }
    }
    if (isPlay) {
        PlayBottomSheetDialog()
    }

}