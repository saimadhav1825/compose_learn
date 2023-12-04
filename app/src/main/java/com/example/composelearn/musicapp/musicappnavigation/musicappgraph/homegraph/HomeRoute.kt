package com.example.composelearn.musicapp.musicappnavigation.musicappgraph.homegraph

sealed class HomeRoute(val route: String) {
    data object RecentPlayerScreen : HomeRoute("RecentPlayerScreen")
    data object RecentSearchScreen : HomeRoute("RecentSearchScreen")
}