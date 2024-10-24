package com.stefanoq21.whatsappgemini.presentation.screen.home.updates

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stefanoq21.whatsappgemini.presentation.theme.WhatsAppGeminiTheme

@Composable
fun UpdatesScreen() {
    Column(
        Modifier
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier.padding(
                start = 16.dp, end = 16.dp, top = 22.dp, bottom = 10.dp
            ),
            text = "Status",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
        )

        Row(
            Modifier
                .fillMaxWidth()
                .clickable { },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .size(48.dp)
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(1.dp),
                    shape = CircleShape,
                    color = Color.LightGray
                ) {
                    Icon(
                        modifier = Modifier.padding(2.dp),
                        imageVector = Icons.Filled.Face,
                        tint = Color.White,
                        contentDescription = null
                    )
                }

                Surface(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .fillMaxWidth(0.45f)
                        .border(1.dp, MaterialTheme.colorScheme.background, CircleShape),
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primary
                ) {
                    Icon(
                        modifier = Modifier.padding(3.dp),
                        imageVector = Icons.Default.Add,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        contentDescription = null
                    )
                }

            }

            Column() {
                Text(
                    modifier = Modifier,
                    text = "My status",
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = "Tap to add status update",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }


        }

        Text(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
            text = "Recent updates",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.titleSmall
        )
    }
}


@Preview(showBackground = true)
@Composable
fun UpdatesScreenPreview() {
    WhatsAppGeminiTheme {
        UpdatesScreen()
    }
}
