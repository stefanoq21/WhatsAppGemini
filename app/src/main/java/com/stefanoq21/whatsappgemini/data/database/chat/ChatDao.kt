package com.stefanoq21.whatsappgemini.data.database.chat

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {
    @Query("INSERT INTO Chat (id) VALUES (NULL)")
    suspend fun insertChat(): Long

    @Insert
    suspend fun insert(chatAttendee: ChatAttendee): Long

    @Transaction
    suspend fun createDirectChat(contactId: Long): Long {
        val chatId = insertChat()
        return insert(ChatAttendee(chatId = chatId, attendeeId = contactId))
    }


    @Delete
    suspend fun delete(chat: Chat)

    @Query("DELETE FROM ChatAttendee WHERE chatId = :chatId")
    suspend fun deleteAttendees(chatId: Long)

    @Transaction
    suspend fun deleteChat(chat: Chat) {
        delete(chat)
        deleteAttendees(chat.id)
    }


    @Query("SELECT * FROM ChatWithLastMessage ORDER BY timestamp DESC")
    fun allDetails(): Flow<List<ChatDetail>>

    @Query("SELECT * FROM ChatWithLastMessage WHERE id = :id")
    suspend fun loadDetailById(id: Long): ChatDetail?

    @Query("SELECT * FROM ChatWithLastMessage WHERE id = :id")
    fun detailById(id: Long): Flow<ChatDetail?>

    @Query("SELECT * FROM ChatWithLastMessage")
    suspend fun loadAllDetails(): List<ChatDetail>


}
