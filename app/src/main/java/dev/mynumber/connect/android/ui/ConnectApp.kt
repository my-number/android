package dev.mynumber.connect.android.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.mynumber.connect.android.ui.components.TopBar
import dev.mynumber.connect.android.ui.screens.Confirm
import dev.mynumber.connect.android.ui.theme.MynaConnectTheme

@Composable
fun ConnectApp() {
    MynaConnectTheme {
        val navController = rememberNavController()
        Scaffold(
            topBar = {
                TopBar()
            }
        ) { innerPadding ->
            NavHost(navController = navController, startDestination = Screens.Confirm.name, modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()){
                for(scr in Screens.values()){
                    composable(scr.name){
                        scr.body(navController)
                    }
                }
            }
        }
    }
}
