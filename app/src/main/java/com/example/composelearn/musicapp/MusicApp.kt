package com.example.composelearn.musicapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composelearn.musicapp.musicappnavigation.musicappgraph.homegraph.homeGraph
import com.example.composelearn.musicapp.musicappnavigation.musicappgraph.settinggraph.settingGraph
import com.example.composelearn.navigation.GraphConstant
import com.example.composelearn.theme.ComposeLearnTheme


sealed class MusicAppScreen(val route: String, val label: String, val icon: ImageVector) {
    data object Home : MusicAppScreen("home", "Home", Icons.Filled.Home)
    data object Favourite : MusicAppScreen("collection", "Favourites", Icons.Filled.Favorite)
    data object PlayList : MusicAppScreen("user_profile", "PlayLists", Icons.Filled.PlayArrow)
    data object Settings : MusicAppScreen("user_profile", "Settings", Icons.Filled.Settings)
}

@Composable
fun MusicApp() {
    ComposeLearnTheme {
        val navController = rememberNavController()
        val onBack: () -> Unit = {
            navController.popBackStack()
        }

        val screens = listOf(
            MusicAppScreen.Home,
            MusicAppScreen.Favourite,
            MusicAppScreen.PlayList,
            MusicAppScreen.Settings
        )
        val showBottomBar = navController
            .currentBackStackEntryAsState().value?.destination?.route in screens.map { it.route }

        Scaffold(
            bottomBar = {
                if (showBottomBar) {
                    BottomNavigation(backgroundColor = MaterialTheme.colors.background) {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination
                        screens.forEach { screen ->
                            BottomNavigationItem(
                                icon = {
                                    Icon(
                                        imageVector = screen.icon,
                                        contentDescription = screen.label
                                    )
                                },
                                label = { Text(screen.label) },
                                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                onClick = {
                                    navController.navigate(screen.route) {
                                        // Pop up to the start destination of the graph to
                                        // avoid building up a large stack of destinations
                                        // on the back stack as users select items
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        // Avoid multiple copies of the same destination when
                                        // reselecting the same item
                                        launchSingleTop = true
                                        // Restore state when reselecting a previously selected item
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                NavHost(
                    navController = navController,
                    startDestination = GraphConstant.HOME_GRAPH
                ) {
                    homeGraph(navController)
                    settingGraph(navController)
                }
            }
        }
    }
}