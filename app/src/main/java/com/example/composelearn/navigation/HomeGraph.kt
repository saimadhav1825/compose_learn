package com.example.composelearn.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.composelearn.musicapp.musicappUi.OnBoardingScreen
import com.example.composelearn.navigation.NavigationRouteConstant.COLLECTIONS
import com.example.composelearn.navigation.NavigationRouteConstant.HOME
import com.example.composelearn.navigation.NavigationRouteConstant.ON_BOARDING_SCREEN
import com.example.composelearn.navigation.NavigationRouteConstant.PROFILE
import com.example.composelearn.navigation.NavigationRouteConstant.SECOND_SCREEN
import com.example.composelearn.ui.auth.LoginScreen
import com.example.composelearn.ui.sampleScreens.FirstScreen
import com.example.composelearn.ui.sampleScreens.SecondScreen

fun NavGraphBuilder.homeGraph(
    navController: NavController
) {
    navigation(startDestination = HOME, route = GraphConstant.HOME_GRAPH) {
        composable(HOME) {
            FirstScreen {
                navController.navigate(SECOND_SCREEN)
            }
        }
        composable(COLLECTIONS) {
            LoginScreen()
        }
        composable(PROFILE) {
            LoginScreen()
        }
        composable(SECOND_SCREEN) {
            SecondScreen()
        }
        composable(ON_BOARDING_SCREEN) {
            OnBoardingScreen{
                navController.navigate(HOME)
            }
        }
    }
}