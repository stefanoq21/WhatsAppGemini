package com.stefanoq21.whatsappgemini.presentation.screen.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.compose.dropUnlessResumed
import com.stefanoq21.whatsappgemini.presentation.component.chat.ChatTopBar
import com.stefanoq21.whatsappgemini.presentation.component.chat.InputBar
import com.stefanoq21.whatsappgemini.presentation.component.chat.MessageList
import com.stefanoq21.whatsappgemini.presentation.navigation.NavigationEvent
import com.stefanoq21.whatsappgemini.presentation.navigation.NavigationViewModel
import com.stefanoq21.whatsappgemini.presentation.theme.LocalExColorScheme
import org.koin.androidx.compose.koinViewModel


@Composable
fun ChatInitScreen(
    chatId: Long,
    navigationViewModel: NavigationViewModel = koinViewModel(viewModelStoreOwner = LocalContext.current as ViewModelStoreOwner),
    chatViewModel: ChatViewModel = koinViewModel(),
) {

    LaunchedEffect(chatId) {
        chatViewModel.onEvent(ChatEvent.OnScreenLaunch(chatId))
    }
    val state by chatViewModel.state.collectAsStateWithLifecycle()

    ChatScreen(
        onNavigationEvent = navigationViewModel::onEvent,
        onEvent = chatViewModel::onEvent,
        state = state
    )

}

@Composable
fun ChatScreen(
    onNavigationEvent: (NavigationEvent) -> Unit,
    onEvent: (ChatEvent) -> Unit,
    state: ChatState,
) {
    Scaffold(
        modifier = Modifier.imePadding(),
        topBar = {
            ChatTopBar(headerInfo = state.headerInfo, onBackPressed = dropUnlessResumed {
                onNavigationEvent(NavigationEvent.OnBack)
            })
        }) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .background(LocalExColorScheme.current.extra.backgroundVariant),
        ) {
            val layoutDirection = LocalLayoutDirection.current
            MessageList(
                messages = state.messages,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                onVideoClick = {},
            )
            InputBar(
                state = state.chatTextFieldState,
                onSendClick = { onEvent(ChatEvent.OnSendMessage) },
                onCameraClick = { },
                onPhotoPickerClick = { },
                modifier = Modifier.fillMaxWidth()
                // .windowInsetsPadding(WindowInsets.ime.exclude(WindowInsets.navigationBars)),
            )
        }
    }
}

