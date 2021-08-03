package dev.mynumber.connect.android.reader.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import dev.mynumber.connect.android.reader.ReaderViewModel
import dev.mynumber.connect.android.reader.ui.Screens
import dev.mynumber.connect.android.reader.ui.components.Modal

@Composable
fun AuthPin(navController: NavController) {
    var pin by remember {
        mutableStateOf("")
    }
    var showPassword by remember {
        mutableStateOf(false)
    }
    var showHelp by remember { mutableStateOf(false) }

    fun next() {
        var store = ReaderViewModel.getInstance()
        store.setPin(pin)
        navController.navigate(Screens.Reader.name)
    }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Type User Authentication PIN", fontSize = 16.sp, modifier = Modifier.padding(8.dp))
        PinInput(pin, showPassword) { value -> pin = value.take(4) }
        Button(
            onClick = { next() }, modifier = Modifier
                .padding(8.dp)
                .width(100.dp)
        ) {
            Text("Next")
        }
        Row(
            Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = showPassword, onCheckedChange = { value -> showPassword = value })
            Text("Show password")
        }
        TextButton(onClick = { showHelp = true }) {
            Text("What is User Authentication PIN?")
        }
    }

    Modal(showHelp, {showHelp = false}) {
        Column() {
            Text("What is User Authentication PIN?",
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp), fontSize = 18.sp)
            Text("It is 4-digit number.")
            Text("It is used to prove that it is really you who is operating the system.")
            Text("It may be different from the other PINs")
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


@Preview(showBackground = true)
@Composable
private fun PreviewAuthPin() {
    AuthPin(navController = rememberNavController())
}