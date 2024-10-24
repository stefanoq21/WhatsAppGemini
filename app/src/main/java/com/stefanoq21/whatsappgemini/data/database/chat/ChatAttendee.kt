package com.stefanoq21.whatsappgemini.data.database.chat

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.stefanoq21.whatsappgemini.data.database.contact.Contact


@Entity(
    primaryKeys = ["chatId", "attendeeId"],
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
            childColumns = ["attendeeId"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [
        Index("chatId", "attendeeId", unique = true),
    ],
)
data class ChatAttendee(
    val chatId: Long,
    val attendeeId: Long,
)
