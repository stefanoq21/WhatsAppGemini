package com.stefanoq21.whatsappgemini.presentation.screen.chat


data class ChatMessage(
    val text: String,
    val mediaUri: String?,
    val mediaMimeType: String?,
    val timestamp: Long,
    val fromMe: Boolean,
    val senderIcon: String?,
    val isFirstMessageFromSender: Boolean,
    val isLastMessageFromDate: Boolean,
)
