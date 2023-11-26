package com.example.composelearn.musicapp.musicappnavigation.musicappgraph.settinggraph
sealed class SettingRoute(val name: String) {
    data object BackupScreen : SettingRoute("BackupScreen")
    data object NotificationScreen : SettingRoute("NotificationScreen")
}