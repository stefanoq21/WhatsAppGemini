package com.stefanoq21.whatsappgemini.presentation.screen.home.chats

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.stefanoq21.whatsappgemini.data.database.chat.ChatDetail
import com.stefanoq21.whatsappgemini.presentation.component.home.chats.ChatsItem
import com.stefanoq21.whatsappgemini.presentation.navigation.NavigationEvent
import com.stefanoq21.whatsappgemini.presentation.navigation.NavigationViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChatsScreen(
    navigationViewModel: NavigationViewModel = koinViewModel(viewModelStoreOwner = LocalContext.current as ViewModelStoreOwner),
    chatsViewModel: ChatsViewModel = koinViewModel(),
) {
    val chats by chatsViewModel.chats.collectAsStateWithLifecycle()



    ChatsInitScreen(
        onNavigationEvent = navigationViewModel::onEvent,
        //onEvent = chatsViewModel::onEvent,
        chats = chats,
    )


}

@Composable
fun ChatsInitScreen(
    onNavigationEvent: (NavigationEvent) -> Unit,
    // onEvent: (ChatsEvent) -> Unit,
    chats: List<ChatDetail>
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {


        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 12.dp),
        ) {
            items(chats) { chat ->
                ChatsItem(
                    chat = chat,
                    onClick = { onNavigationEvent(NavigationEvent.OnNavigateToChat(chat.chatWithLastMessage.id)) },
                )

            }
        }


    }

}




