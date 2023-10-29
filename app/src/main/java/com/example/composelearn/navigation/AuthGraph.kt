package com.example.composelearn.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.composelearn.navigation.NavigationRouteConstant.LOGIN_SCREEN
import com.example.composelearn.navigation.NavigationRouteConstant.SPLASH_SCREEN
import com.example.composelearn.ui.SplashScreen
import com.example.composelearn.ui.auth.LoginScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavController
) {
    navigation(startDestination = SPLASH_SCREEN, route = GraphConstant.AUTH_GRAPH) {
        composable(SPLASH_SCREEN) {
            SplashScreen {
                navController.navigate(it)
            }
        }
        composable(LOGIN_SCREEN) {
            LoginScreen()
        }
    }
}