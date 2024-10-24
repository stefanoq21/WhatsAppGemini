package com.stefanoq21.whatsappgemini.data.database

import android.content.Context
import com.stefanoq21.whatsappgemini.data.database.chat.ChatDetail
import com.stefanoq21.whatsappgemini.data.database.message.Message
import kotlinx.coroutines.flow.Flow

class MyDatabaseRepository(
    private val bookDatabase: MyDatabase,
    private val context: Context
) {

    fun getChats(): Flow<List<ChatDetail>> {
        return bookDatabase.chatDao().allDetails()
    }

    fun getChat(chatId: Long): Flow<ChatDetail?> {
        return bookDatabase.chatDao().detailById(chatId)
    }

    fun getMessages(chatId: Long): Flow<List<Message>> {
        return bookDatabase.messageDao().allByChatId(chatId)
    }

    suspend fun insertMessage(
        chatId: Long,
        text: String,
        senderId: Long,
        mediaUri: String?,
        mediaMimeType: String?,
    ) {
        bookDatabase.messageDao().insert(
            Message(
                id = 0L,
                chatId = chatId,
                senderId = senderId,
                text = text,
                mediaUri = mediaUri,
                mediaMimeType = mediaMimeType,
                timestamp = System.currentTimeMillis(),
            ),
        )
    }
}