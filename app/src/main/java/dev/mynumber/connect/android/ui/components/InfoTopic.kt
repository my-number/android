package dev.mynumber.connect.android.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.mynumber.connect.android.ui.theme.InfoTopicIconBg

@Composable
fun InfoTopic(
    topic: String,
    icon: ImageVector? = null,
    color: Color = InfoTopicIconBg
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(30.dp),
            shape = CircleShape,
            color = color
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = topic,
                    tint = Color.White,
                    modifier = Modifier.padding(6.dp)
                )
            }

        }
        Text(text = topic, fontSize = 16.sp, modifier = Modifier.padding(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewInfoTopic() {
    InfoTopic(topic = "Digital Signature", icon = Icons.Filled.Face)
}