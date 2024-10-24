package com.stefanoq21.whatsappgemini.data.database.message

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.stefanoq21.whatsappgemini.data.database.chat.Chat
import com.stefanoq21.whatsappgemini.data.database.contact.Contact

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Chat::class,
            parentColumns = ["id"],
            childColumns = ["chatId"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = Contact::class,
            parentColumns = ["id"],
            childColumns = ["senderId"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [
        Index("chatId"),
        Index("senderId"),
    ],
)
data class Message(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val chatId: Long,
    val senderId: Long,
    val text: String,
    val mediaUri: String?,
    val mediaMimeType: String?,
    val timestamp: Long,
)

