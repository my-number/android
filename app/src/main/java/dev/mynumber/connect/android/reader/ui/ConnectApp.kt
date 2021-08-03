package dev.mynumber.connect.android.reader.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.mynumber.connect.android.reader.ui.components.TopBar
import dev.mynumber.connect.android.reader.ui.theme.MynaConnectTheme

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
