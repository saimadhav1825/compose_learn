package com.example.composelearn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.composelearn.musicapp.MusicApp
import com.example.composelearn.theme.ComposeLearnTheme
import com.example.composelearn.viewmodels.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        //Slash api
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition{viewModel.isLoading.value}

        setContent {
            ComposeLearnTheme {
                MusicApp()
            }
        }
    }
}
