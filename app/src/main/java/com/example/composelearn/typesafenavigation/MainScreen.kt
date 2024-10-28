package com.example.composelearn.typesafenavigation

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenA) {
        composable<ScreenA> {
            Button(onClick = {
                navController.navigate(ScreenB(name = "sai"))
            }) {
                Text(text = "Show")
            }
        }
        composable<ScreenB> {
            val data = it.toRoute<ScreenB>()

            Text(text = data.name.toString())
        }
    }
}

@Serializable
object ScreenA

@Serializable
data class ScreenB(
    var name: String? = null
)