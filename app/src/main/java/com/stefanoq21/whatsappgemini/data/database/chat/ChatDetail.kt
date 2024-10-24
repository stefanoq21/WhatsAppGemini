package com.stefanoq21.whatsappgemini.data.database.chat

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.stefanoq21.whatsappgemini.data.database.contact.Contact

data class ChatDetail(
    @Embedded
    val chatWithLastMessage: ChatWithLastMessage,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ChatAttendee::class,
            parentColumn = "chatId",
            entityColumn = "attendeeId",
        ),
    )
    val attendees: List<Contact>,
)