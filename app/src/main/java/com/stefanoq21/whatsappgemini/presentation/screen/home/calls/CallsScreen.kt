package com.stefanoq21.whatsappgemini.presentation.screen.home.calls

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.AddIcCall
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stefanoq21.whatsappgemini.presentation.theme.WhatsAppGeminiTheme

@Composable
fun CallsScreen() {
    Column(Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.padding(
                start = 16.dp, end = 16.dp, top = 22.dp, bottom = 8.dp
            ),
            text = "Favorites",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
        )

        Row(
            Modifier
                .fillMaxWidth()
                .clickable { },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .size(40.dp),
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    modifier = Modifier.padding(10.dp),
                    imageVector = Icons.Filled.Favorite,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = null
                )
            }

            Text(
                modifier = Modifier,
                text = "Add to Favorites",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
        }


        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            val myId = "inlineContent"
            val text = buildAnnotatedString {
                append("To start calling contacts who have WhatsApp, tap ")
                appendInlineContent(myId, "[icon]")
                append(" at the bottom of your screen.")
            }

            val inlineContent = mapOf(
                myId to InlineTextContent(
                    Placeholder(
                        width = MaterialTheme.typography.bodyLarge.fontSize,
                        height = MaterialTheme.typography.bodyLarge.fontSize,
                        placeholderVerticalAlign = PlaceholderVerticalAlign.TextBottom
                    )
                ) {
                    Icon(
                        Icons.Outlined.AddIcCall,
                        null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

            )

            Text(
                modifier = Modifier.fillMaxWidth(0.8f),
                text = text,
                inlineContent = inlineContent,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center

            )
        }
    }


}

@Preview(showBackground = true)
@Composable
fun CallsScreenPreview() {
    WhatsAppGeminiTheme {
        CallsScreen()
    }
}
