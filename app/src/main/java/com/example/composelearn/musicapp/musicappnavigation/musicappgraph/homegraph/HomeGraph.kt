package com.example.composelearn.musicapp.musicappnavigation.musicappgraph.homegraph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.composelearn.musicapp.MusicAppScreen
import com.example.composelearn.musicapp.musicappUi.homescreenui.HomeScreen
import com.example.composelearn.musicapp.musicappUi.settingsmoduleui.SettingScreen
import com.example.composelearn.navigation.GraphConstant
import com.example.composelearn.navigation.NavigationRouteConstant
import com.example.composelearn.ui.auth.LoginScreen
import com.example.composelearn.ui.sampleScreens.FirstScreen

fun NavGraphBuilder.homeGraph(
    navController: NavController
) {
    navigation(startDestination = MusicAppScreen.Home.route, route = GraphConstant.HOME_GRAPH) {
        composable(NavigationRouteConstant.HOME) {
            HomeScreen()
        }
        composable(MusicAppScreen.Favourite.route) {
            LoginScreen()
        }
        composable(MusicAppScreen.PlayList.route) {
            LoginScreen()
        }
        composable(MusicAppScreen.Settings.route) {
            SettingScreen()
        }
    }
}