package dev.mynumber.connect.android.reader

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.mynumber.connect.android.reader.ui.ConnectApp

class ReaderActivity : ComponentActivity() {
    val mynaManager = MynaManager.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        mynaManager.prepareNfc(this);
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContent {
            ConnectApp()
        }
    }

    override fun onNewIntent(intent: Intent?) {
        if (intent != null) {
            mynaManager.onNewIntent(intent)
        }
    }
}