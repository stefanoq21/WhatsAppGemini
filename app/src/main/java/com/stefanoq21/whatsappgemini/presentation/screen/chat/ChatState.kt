package com.stefanoq21.whatsappgemini.presentation.screen.chat

import androidx.compose.foundation.text.input.TextFieldState
import com.stefanoq21.whatsappgemini.data.database.contact.Contact

data class ChatState(
    val headerInfo: HeaderInfo? = null,
    val messages: List<ChatMessage> = emptyList(),
    val chatTextFieldState: TextFieldState = TextFieldState(),
    val attendees: List<Contact> = emptyList(),

    )