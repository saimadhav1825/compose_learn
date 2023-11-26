package com.example.composelearn.musicapp.musicappnavigation.musicappgraph.settinggraph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.composelearn.musicapp.musicappnavigation.GraphConstants
import com.example.composelearn.ui.auth.LoginScreen

fun NavGraphBuilder.settingGraph(navController:NavController) {
    navigation(startDestination = SettingRoute.NotificationScreen.name, route = GraphConstants.SETTINGS_GRAPH) {
        composable(SettingRoute.NotificationScreen.name) {
            LoginScreen()
        }
    }
}