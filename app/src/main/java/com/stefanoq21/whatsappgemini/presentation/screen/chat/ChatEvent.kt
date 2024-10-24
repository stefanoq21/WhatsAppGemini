package com.stefanoq21.whatsappgemini.presentation.screen.chat


sealed interface ChatEvent {
    data class OnScreenLaunch(val chatId: Long) : ChatEvent

    data object OnSendMessage : ChatEvent
}