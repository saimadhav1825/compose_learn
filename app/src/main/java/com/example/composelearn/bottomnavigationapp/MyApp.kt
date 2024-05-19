package com.example.composelearn.bottomnavigationapp


import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navigation
import com.example.composelearn.R
import com.example.composelearn.navigation.GraphConstant
import com.google.gson.Gson

@Composable
fun MyApp() {
    val appState = rememberMyAppState()
    Scaffold(
        bottomBar = {
            if (appState.shouldShowBottomBar) {
                TMDbBottomBar(
                    tabs = appState.bottomBarTabs,
                    currentRoute = appState.currentRoute!!,
                    navigateToRoute = appState::navigateToBottomBarRoute,
                )
            }
        }
    ) { innerPaddingModifier ->
        NavHost(
            navController = appState.navController,
            startDestination = MainDestinations.HOME_ROUTE,
            modifier = Modifier.padding(innerPaddingModifier)
        ) {
            navigationScreens(
                onMovieSelected = appState::navigateToMovieDetail,
                onTVShowSelected = appState::navigateToTVShowDetail,
                onSearchMovie = appState::navigateToSearchMovie,
                onSearchTVShow = appState::navigateToSearchTVShow,
                navController = appState.navController
            )
            homeNavGraph()
        }
    }
}

@Composable
private fun TMDbBottomBar(
    tabs: Array<HomeSections>,
    currentRoute: String,
    navigateToRoute: (String) -> Unit
) {
    val currentSection = tabs.first { it.route == currentRoute }

    Box(
        Modifier.navigationBarsPadding()
    ) {
        BottomNavigation(backgroundColor = MaterialTheme.colors.background, elevation = 0.dp) {
            tabs.forEach { section ->
                val selected = section == currentSection
                BottomNavigationItem(
                    label = {
                        Text(text = stringResource(id = section.title))
                    },
                    icon = {
                        Icon(
                            imageVector = if (selected) section.selectedIcon else section.unselectedIcon,
                            contentDescription = stringResource(id = section.title)
                        )
                    },
                    selected = selected,
                    unselectedContentColor = MaterialTheme.colors.onBackground.copy(alpha = ContentAlpha.disabled),
                    selectedContentColor = MaterialTheme.colors.onBackground,
                    onClick = { navigateToRoute(section.route) }
                )
            }
        }
    }
}

private fun NavGraphBuilder.navigationScreens(
    onMovieSelected: (Int) -> Unit,
    onTVShowSelected: (Int) -> Unit,
    onSearchMovie: () -> Unit,
    onSearchTVShow: () -> Unit,
    navController: NavController
) {
    navigation(
        route = MainDestinations.HOME_ROUTE,
        startDestination = HomeSections.MOVIE_SECTION.route
    ) {
        composable(route = HomeSections.MOVIE_SECTION.route) {
            Text(text = "MOVIE_SECTION", modifier = Modifier.clickable {
                onMovieSelected.invoke(1)
            })
        }
        composable(route = HomeSections.TV_SHOW_SECTION.route) {
            Text(text = "TV_SHOW_SECTION", modifier = Modifier.clickable {
                onMovieSelected.invoke(1)
            })
        }
        composable(route = HomeSections.BOOKMARK_SECTION.route) {
            Text(text = "BOOKMARK_SECTION")
        }
        composable(route = HomeSections.SETTING_SECTION.route) {
            Text(text = "SETTING_SECTION")
        }
    }
}

fun NavGraphBuilder.homeNavGraph(
) {
    navigation(
        startDestination = MainDestinations.TMDB_MOVIE_DETAIL_ROUTE,
        route = GraphConstant.HOME_GRAPH
    ) {
        composable(MainDestinations.TMDB_MOVIE_DETAIL_ROUTE) {
            Text(text = "TMDB_MOVIE_DETAIL_ROUTE")
        }
    }
}

enum class HomeSections(
    val route: String,
    @StringRes val title: Int,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
) {
    MOVIE_SECTION("Movie", R.string.movie, Icons.Outlined.Home, Icons.Filled.Home),
    TV_SHOW_SECTION("TVShow", R.string.tv_show, Icons.Outlined.PlayArrow, Icons.Filled.PlayArrow),
    BOOKMARK_SECTION("Bookmark", R.string.favorite, Icons.Outlined.Favorite, Icons.Filled.Favorite),
    SETTING_SECTION("Setting", R.string.setting, Icons.Outlined.Settings, Icons.Filled.Settings)
}

private val gson = Gson()