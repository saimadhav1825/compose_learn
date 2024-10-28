package com.example.composelearn.recomposition

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.composelearn.theme.ComposeLearnTheme
import com.example.composelearn.viewmodels.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RecompositionActivity : ComponentActivity() {
    private val viewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLearnTheme {
                RecompostionMainScreen()
            }
        }
    }
}
