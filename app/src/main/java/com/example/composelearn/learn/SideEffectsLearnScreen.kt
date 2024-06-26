package com.example.composelearn.learn

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SideEffectsLearnScreen() {
    val sideEffectsViewModel: SideEffectsViewModel = hiltViewModel()

    val snackbarHostState = remember { SnackbarHostState() }
    var count by rememberSaveable {
        mutableIntStateOf(0)
    }
    val scope = rememberCoroutineScope()
    LaunchedEffect(count) {
        println("Count $count")
    }
    println("Out Side Count $count")
    Scaffold(modifier = Modifier.fillMaxSize(), snackbarHost = {
        SnackbarHost(hostState = snackbarHostState)
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            Button(onClick = {
                count++
            }) {
                Text(text = "Launch Effect")

            }
            Button(onClick = {
                scope.launch {
                    snackbarHostState.showSnackbar("rememberCoroutineScope")
                }
            }) {
                Text(text = "rememberCoroutineScope")
            }
        }
    }
    SampleRememberUpdateStat {
        println("SampleRememberUpdateStat")
    }
    SampleDisposableEffect(onStart = { println("onStart") }) {
        println("onStop")
    }
    SideEffect {

        println("SideEffect")
    }

}

@Composable
fun SampleRememberUpdateStat(onTimeOut: () -> Unit) {

    val updateTimeOut by rememberUpdatedState(newValue = onTimeOut)
    LaunchedEffect(true) {
        delay(1000L)
        updateTimeOut()

    }
}

@Composable
fun SampleDisposableEffect(onStart: () -> Unit, onStop: () -> Unit) {

    val onStartTimOut by rememberUpdatedState(newValue = onStart)
    val onStopTimeOut by rememberUpdatedState(newValue = onStop)
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val obererver = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                onStartTimOut()
            }
            if (event == Lifecycle.Event.ON_STOP) {
                onStopTimeOut()
            }

        }
        lifecycleOwner.lifecycle.addObserver(observer = obererver)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(obererver)
        }
    }
}