package com.example.composelearn.musicapp.musicappnavigation.musicappgraph.homegraph

sealed class HomeRoute (val name: String) {
        data object HomeScreen : HomeRoute("HomeScreen")
}