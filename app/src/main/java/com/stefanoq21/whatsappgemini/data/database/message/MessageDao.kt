package com.stefanoq21.whatsappgemini.data.database.message

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {

    @Insert
    suspend fun insert(message: Message): Long

    /**
     * Obtains a list of all messages in the chat. Newer messages come first.
     */
    @Query("SELECT * FROM Message WHERE chatId = :chatId ORDER BY timestamp DESC")
    fun allByChatId(chatId: Long): Flow<List<Message>>

    /**
     * Obtains a list of all messages in the chat. Newer messages come first.
     */
    @Query("SELECT * FROM Message WHERE chatId = :chatId ORDER BY timestamp DESC")
    suspend fun loadAll(chatId: Long): List<Message>

    @Query("DELETE FROM Message")
    suspend fun clearAll()
}
