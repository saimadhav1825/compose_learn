package com.example.composelearn.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.composelearn.navigation.NavigationRouteConstant.FIRST_SCREEN
import com.example.composelearn.navigation.NavigationRouteConstant.SECOND_SCREEN
import com.example.composelearn.ui.splash.SplashScreen
import com.example.composelearn.ui.auth.LoginScreen
import com.example.composelearn.ui.sampleScreens.FirstScreen

fun NavGraphBuilder.sampleGraph(
    navController: NavController
) {
    navigation(startDestination = FIRST_SCREEN, route = GraphConstant.AUTH_GRAPH) {
        composable(FIRST_SCREEN) {
            FirstScreen {
                navController.navigate(SECOND_SCREEN)
            }
        }
        composable(SECOND_SCREEN) {
            LoginScreen()
        }
    }
}