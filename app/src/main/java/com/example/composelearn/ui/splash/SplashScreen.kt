package com.example.composelearn.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.composelearn.R
import com.example.composelearn.navigation.NavigationRouteConstant.LOGIN_SCREEN
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigateTo: (String) -> Unit) {
    LaunchedEffect(true) {
        delay(200)
        onNavigateTo.invoke(LOGIN_SCREEN)
    }
    Box {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Splash Image",
            modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Fit
        )
    }
}