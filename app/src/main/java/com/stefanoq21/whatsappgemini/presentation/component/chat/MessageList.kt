package com.stefanoq21.whatsappgemini.presentation.component.chat

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.stefanoq21.whatsappgemini.domain.utils.TimeDateUtils.toMessageListString
import com.stefanoq21.whatsappgemini.presentation.screen.chat.ChatMessage


@Composable
fun MessageList(
    messages: List<ChatMessage>,
    modifier: Modifier = Modifier,
    onVideoClick: (uri: String) -> Unit = {},
) {
    val context = LocalContext.current
    Box(modifier = modifier) {

        val state = rememberLazyListState()

        LazyColumn(
            state = state,
            modifier = Modifier.fillMaxSize(),
            reverseLayout = true,
            contentPadding = PaddingValues(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Bottom),
        ) {


            items(items = messages) { message ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    if (message.isLastMessageFromDate) {
                        MessageHeader(header = message.timestamp.toMessageListString(context))
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = if (message.fromMe) 64.dp else 8.dp,
                                end = if (message.fromMe) 8.dp else 64.dp,
                                top = if (message.isFirstMessageFromSender) 8.dp else 0.dp
                            ),
                        horizontalArrangement = Arrangement.spacedBy(
                            0.dp,
                            if (message.fromMe) Alignment.End else Alignment.Start,
                        ),
                        verticalAlignment = Alignment.Top,
                    ) {

                        if (message.senderIcon != null) {
                            ChatContactIcon(icon = message.senderIcon)
                        }
                        MessageBubble(
                            message = message,
                            onVideoClick = { message.mediaUri?.let { onVideoClick(it) } },
                        )
                    }
                }
            }

        }
        var header by remember { mutableStateOf("") }

        LaunchedEffect(state, messages) {
            snapshotFlow { state.layoutInfo }.collect {
                val lastVisibleItemIndex = it.visibleItemsInfo.lastOrNull()?.index
                if (lastVisibleItemIndex != null && lastVisibleItemIndex in messages.indices) {
                    header = messages[lastVisibleItemIndex].timestamp.toMessageListString(context)
                }
            }
        }


        AnimatedVisibility(
            modifier = Modifier
                .align(Alignment.TopCenter),
            visible = state.canScrollBackward,
            enter = slideInVertically(initialOffsetY = { -it }),
            exit = slideOutVertically(targetOffsetY = { -it }),
        ) {
            MessageHeader(header = header)
        }

    }
}