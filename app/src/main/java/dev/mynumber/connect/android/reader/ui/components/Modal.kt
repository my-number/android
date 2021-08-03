package dev.mynumber.connect.android.reader.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun Modal(visible: Boolean, onClose: () -> Unit, content: @Composable ()->Unit) {
    if(!visible){
        return;
    }
    Surface(
        color = Color(0xcc000000),
        modifier = Modifier.zIndex(100f).clickable { onClose() }
    ) {
        Box(
            Modifier
                .padding(16.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                Modifier
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16))
                    .background(Color.White)
            ) {
                Column(Modifier.padding(16.dp)
                ) {
                    content()
                }
            }
        }

    }
}
@Preview(showSystemUi = true)
@Composable
private fun PreviewHelpModal(){

}