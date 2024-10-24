package com.stefanoq21.whatsappgemini.presentation.component.chat

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.stefanoq21.whatsappgemini.domain.utils.TimeDateUtils.toMessageBubbleString
import com.stefanoq21.whatsappgemini.presentation.screen.chat.ChatMessage
import com.stefanoq21.whatsappgemini.presentation.theme.WhatsAppGeminiTheme


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MessageBubble(
    message: ChatMessage,
    modifier: Modifier = Modifier,
    onVideoClick: () -> Unit = {},
) {
    val tailHeight = 12.dp
    val tailWidth = 12.dp
    Surface(
        modifier = modifier,
        color = if (message.fromMe) {
            MaterialTheme.colorScheme.secondaryContainer
        } else {
            MaterialTheme.colorScheme.secondary
        },
        shape = if (message.isFirstMessageFromSender) WhatsAppBubbleShape(
            cornerRadius = with(LocalDensity.current) { 16.dp.toPx() },
            tailWidth = with(LocalDensity.current) { tailWidth.toPx() },
            tailHeight = with(LocalDensity.current) { tailHeight.toPx() },
            isIncoming = !message.fromMe
        ) else WhatsAppBubbleNextMessageShape(
            cornerRadius = with(LocalDensity.current) { 16.dp.toPx() },
            tailWidth = with(LocalDensity.current) { tailWidth.toPx() },
            isIncoming = !message.fromMe
        ),
        shadowElevation = 1.dp,
    ) {
        Column(
            modifier = Modifier.padding(
                start = if (message.fromMe) 8.dp else 8.dp + tailWidth,
                end = if (message.fromMe) 8.dp + tailWidth else 8.dp,
                top = 8.dp,
                bottom = 2.dp
            ),
        ) {

            if (message.mediaUri != null) {
                val mimeType = message.mediaMimeType
                if (mimeType != null) {
                    if (mimeType.contains("image")) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(message.mediaUri).build(),
                            contentDescription = null,
                            modifier = Modifier
                                .height(250.dp)
                                .padding(10.dp),
                        )
                    } else if (mimeType.contains("video")) {
                        VideoMessagePreview(
                            videoUri = message.mediaUri,
                            onClick = onVideoClick,
                        )
                    } else {
                        Log.e("MessageBubble", "Unrecognized media type")
                    }
                } else {
                    Log.e("MessageBubble", "No MIME type associated with media object")
                }
            }

            FlowRow(
                Modifier,
                horizontalArrangement = Arrangement.End,
            ) {
                Text(text = message.text)
                Box(
                    Modifier
                        .padding(start = 10.dp, top = 10.dp)
                        .fillMaxRowHeight(1f)
                ) {
                    Row(
                        modifier = Modifier.align(Alignment.BottomEnd),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        val myId = "inlineContent"
                        val text = buildAnnotatedString {
                            append(message.timestamp.toMessageBubbleString())
                            if (message.fromMe) {
                                append(" ")
                                appendInlineContent(myId, "[icon]")
                            }
                        }

                        val inlineContent = mapOf(myId to InlineTextContent(
                            Placeholder(
                                width = MaterialTheme.typography.bodyLarge.fontSize,
                                height = MaterialTheme.typography.bodyLarge.fontSize,
                                placeholderVerticalAlign = PlaceholderVerticalAlign.TextBottom
                            )
                        ) {

                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        })

                        Text(
                            text = text,
                            inlineContent = inlineContent,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,

                            )
                    }
                }
            }


        }
    }
}


@Preview
@Composable
fun MessageBubblePrew() {
    WhatsAppGeminiTheme {
        Column(Modifier.fillMaxSize()) {
            MessageBubble(
                ChatMessage(
                    "aaaaa", null, null, 0, true, null, true, false
                )
            )
            MessageBubble(
                ChatMessage(
                    "aaaaa", null, null, 0, true, null, false, false
                )
            )
            MessageBubble(
                ChatMessage(
                    "aaasd das aaa",
                    null,
                    null,
                    0,
                    false,
                    null,
                    true, false
                )
            )
            MessageBubble(
                ChatMessage(
                    "aaasd das aaa",
                    null,
                    null,
                    0,
                    false,
                    null,
                    false, false
                )
            )


        }
    }
}

class WhatsAppBubbleShape(
    private val cornerRadius: Float,
    private val tailWidth: Float,
    private val tailHeight: Float,
    private val isIncoming: Boolean
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path()
        val width = size.width
        val height = size.height
        val tailCurve = 10f
        val tailOffset = if (isIncoming) tailWidth else width - tailWidth
        path.moveTo(tailOffset, 0f) // Tail start

        path.lineTo(if (isIncoming) tailCurve else width - tailCurve, 0f)
        path.quadraticTo(// Rounded tail corner
            if (isIncoming) 0f else width,
            0f,
            if (isIncoming) tailCurve else width - tailCurve,
            tailCurve
        )

        path.lineTo(
            tailOffset,
            tailHeight
        ) // Tail top/bottom
        path.lineTo(
            tailOffset,
            0f
        ) // Tail end

        // Rounded rectangle
        path.addRoundRect(
            RoundRect(
                rect = Rect(
                    offset = Offset(if (isIncoming) tailWidth else 0f, 0f),
                    size = Size(width - tailWidth, height)
                ),
                topLeft = if (isIncoming) CornerRadius.Zero else CornerRadius(cornerRadius),
                topRight = if (isIncoming) CornerRadius(cornerRadius) else CornerRadius.Zero,
                bottomRight = CornerRadius(cornerRadius),
                bottomLeft = CornerRadius(cornerRadius),
            )
        )

        path.close()

        return Outline.Generic(path)
    }
}

class WhatsAppBubbleNextMessageShape(
    private val cornerRadius: Float,
    private val tailWidth: Float,
    private val isIncoming: Boolean
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path()
        val width = size.width
        val height = size.height

        // Rounded rectangle
        path.addRoundRect(
            RoundRect(
                rect = Rect(
                    offset = Offset(if (isIncoming) tailWidth else 0f, 0f),
                    size = Size(width - tailWidth, height)
                ),
                topLeft = CornerRadius(cornerRadius),
                topRight = CornerRadius(cornerRadius),
                bottomRight = CornerRadius(cornerRadius),
                bottomLeft = CornerRadius(cornerRadius),
            )
        )

        path.close()

        return Outline.Generic(path)
    }
}