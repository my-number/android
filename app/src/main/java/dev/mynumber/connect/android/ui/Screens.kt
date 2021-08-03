package dev.mynumber.connect.android.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import dev.mynumber.connect.android.ui.screens.*


enum class Screens(
    val body: @Composable (NavController) -> Unit
) {
    Confirm(
        body = { navController ->
            Confirm(navController)
        }
    ),
    AuthPin(
        body = { navController ->
            AuthPin(navController)
        }
    ),
    Reader(
        body = { navController ->
            Reader(navController)
        }
    ),
}