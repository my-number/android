package dev.mynumber.connect.android

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import dev.mynumber.connect.android.reader.ReaderActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch {
            delay(1000)
            val intent = Intent(this@MainActivity, ReaderActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}