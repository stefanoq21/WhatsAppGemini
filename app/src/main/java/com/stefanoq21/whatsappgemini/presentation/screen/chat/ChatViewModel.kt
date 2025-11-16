package com.stefanoq21.whatsappgemini.presentation.screen.chat

import androidx.compose.foundation.text.input.clearText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stefanoq21.whatsappgemini.data.ChatRepository
import com.stefanoq21.whatsappgemini.data.database.MyDatabaseRepository
import com.stefanoq21.whatsappgemini.domain.utils.TimeDateUtils.toDay
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class ChatViewModel(
    private val myDatabaseRepository: MyDatabaseRepository,
    private val chatRepository: ChatRepository,
) : ViewModel() {
    private val chatId = MutableStateFlow(0L)


    private val chat = chatId.flatMapLatest { id -> myDatabaseRepository.getChat(id) }

    private val dbMessages = chatId.flatMapLatest { id -> myDatabaseRepository.getMessages(id) }

    private val messages = combine(dbMessages, chat) { messages, chat ->
        // Build a list of `ChatMessage` from this list of `Message`.
        buildList {
            messages.forEachIndexed { index, message ->
                val nextSenderId =
                    if (index < messages.size - 1) messages[index + 1].senderId else null
                val isFirstMessageFromSender = nextSenderId != message.senderId

                val messageDate = message.timestamp.toDay()
                val nextMessageDate =
                    if (index < messages.size - 1) messages[index + 1].timestamp.toDay() else null
                val isLastMessageFromDate = nextMessageDate != messageDate

                // Show the contact icon only if we have more attendees
                val showIcon = chat?.attendees?.size?.takeIf { it > 1 } != null
                val iconUri =
                    if (showIcon)
                        chat.attendees.firstOrNull { it.id == message.senderId }?.icon
                    else null
                add(
                    ChatMessage(
                        text = message.text,
                        mediaUri = message.mediaUri,
                        mediaMimeType = message.mediaMimeType,
                        timestamp = message.timestamp,
                        fromMe = message.senderId == 0L,
                        senderIcon = iconUri,
                        isFirstMessageFromSender = isFirstMessageFromSender,
                        isLastMessageFromDate = isLastMessageFromDate
                    ),
                )
            }
        }
    }

    private val headerInfo = chat.map { chat ->
        val isGroup = chat?.attendees?.size?.let { it > 1 } ?: false
        val attendee = chat?.attendees?.firstOrNull()
        attendee?.let {
            HeaderInfo(attendee.name, attendee.icon, isGroup)
        }
        //need to be done the header for the group
    }

    val state = combine(headerInfo, messages, chat) { header, messages, chat ->
        ChatState(
            headerInfo = header,
            messages = messages,
            attendees = chat?.attendees ?: emptyList(),
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), ChatState())


    fun onEvent(event: ChatEvent) {
        when (event) {
            is ChatEvent.OnScreenLaunch -> {
                performOnScreenStart(event.chatId)

            }

            ChatEvent.OnSendMessage -> {
                sendMessage()
            }
        }
    }

    private fun performOnScreenStart(chatId: Long) {
        this.chatId.value = chatId
    }

    private fun sendMessage() {
        viewModelScope.launch {
            chatRepository.sendMessage(
                chatId = chatId.value,
                text = state.value.chatTextFieldState.text.toString(),
                mediaUri = null,
                mediaMimeType = null,
                contact = state.value.attendees.firstOrNull(),
                messages = state.value.messages,
            )
            state.value.chatTextFieldState.clearText()
        }
    }


}

