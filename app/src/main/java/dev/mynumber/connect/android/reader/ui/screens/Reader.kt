package dev.mynumber.connect.android.reader.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.mynumber.connect.android.reader.MynaManager
import dev.mynumber.connect.android.reader.ReaderViewModel


@Composable
fun Reader(navController: NavController) {

    var store = ReaderViewModel.getInstance()

    val mynaManager = MynaManager.getInstance()
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Please touch your card", fontSize = 16.sp, modifier = Modifier.padding(8.dp))
    }
    DisposableEffect(navController) {
        mynaManager.enableRead()
        onDispose {
            mynaManager.disableRead()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewReader() {
    Reader(navController = rememberNavController())
}