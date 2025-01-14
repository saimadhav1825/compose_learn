package com.example.composelearn.musicapp.musicappnavigation.musicappgraph.settinggraph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.composelearn.musicapp.musicappUi.settingsmoduleui.NotificationScreen
import com.example.composelearn.musicapp.musicappnavigation.GraphConstants

fun NavGraphBuilder.settingGraph(navController: NavController, onBack: () -> Unit = {}) {
    navigation(
        startDestination = SettingRoute.NotificationScreen.name,
        route = GraphConstants.SETTINGS_GRAPH
    ) {
        composable(SettingRoute.NotificationScreen.name) {
            NotificationScreen(onBack = onBack)
        }
    }
}