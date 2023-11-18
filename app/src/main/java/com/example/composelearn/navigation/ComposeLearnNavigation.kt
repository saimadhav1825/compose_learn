package com.example.composelearn.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.composelearn.navigation.GraphConstant.AUTH_GRAPH
import com.example.composelearn.navigation.NavigationRouteConstant.FIRST_SCREEN

@Composable
fun ComposeLearnNavigation(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController, route = FIRST_SCREEN,
        startDestination = AUTH_GRAPH,
    ) {
        sampleGraph(navController)
    }
}