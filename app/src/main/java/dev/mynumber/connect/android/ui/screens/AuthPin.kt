package dev.mynumber.connect.android.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.mynumber.connect.android.ui.Screens

@Composable
fun AuthPin(navController: NavController) {
    var pin by remember {
        mutableStateOf("")
    }
    var showPassword by remember {
        mutableStateOf(false)
    }
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Type PIN", fontSize = 16.sp, modifier = Modifier.padding(8.dp))
        PinInput(pin, showPassword) { value -> pin = value.take(4) }
        Button(onClick = { navController.navigate(Screens.Reader.name)  }, modifier = Modifier.padding(8.dp).width(100.dp) ) {
            Text("Next")
        }
        Row(Modifier.padding(8.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = showPassword, onCheckedChange = { value -> showPassword = value })
            Text("Show password")
        }
    }
}

@Composable
internal fun PinInput(pin: String, showPassword: Boolean, onValueChange: (String) -> Unit) {
    TextField(
        value = pin,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        singleLine = true,
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = Modifier,
        textStyle = TextStyle(fontSize = 32.sp, textAlign = TextAlign.Center)
    )
}

@Preview (showBackground = true)
@Composable
private fun PreviewAuthPin() {
    AuthPin(navController = rememberNavController())
}