package dev.mynumber.connect.android.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.mynumber.connect.android.ui.Screens
import dev.mynumber.connect.android.ui.components.InfoTopic
import dev.mynumber.connect.android.ui.theme.InfoTopicIconBgErr
import dev.mynumber.connect.android.ui.theme.InfoTopicIconBgOk

@Composable
fun Confirm(navController: NavController) {
    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize(),

        verticalArrangement = Arrangement.SpaceBetween,
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                Modifier.padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("AppName", fontWeight = FontWeight.Bold, fontSize = 32.sp)
                Text(
                    "would like to read your card",
                    fontWeight = FontWeight.Light,
                    fontSize = 24.sp
                )
            }
            Text("The following information will be sent", modifier = Modifier.padding(4.dp))
            Column(horizontalAlignment = Alignment.Start) {
                InfoTopic("Digital Signature", Icons.Filled.ThumbUp, InfoTopicIconBgOk)
                InfoTopic("Certificate(Auth)", null, InfoTopicIconBgOk)
            }
            Text("The following information will not be sent", modifier = Modifier.padding(4.dp))
            Column(horizontalAlignment = Alignment.Start) {
                InfoTopic("Digital Signature", Icons.Filled.ThumbUp, InfoTopicIconBgErr)
                InfoTopic("Certificate(Auth)", null, InfoTopicIconBgErr)
            }
        }
        Column(Modifier.fillMaxWidth()) {
            Button(onClick = {
                Log.d("foo", "bar")
                navController.navigate(Screens.AuthPin.name)
            }, modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(12.dp)), shape = MaterialTheme.shapes.medium) {
                Text("Confirm", fontSize = 20.sp)
            }
            TextButton(onClick = { /*TODO*/ }, modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(12.dp))) {
                Text("Cancel")
            }
        }
    }
}
