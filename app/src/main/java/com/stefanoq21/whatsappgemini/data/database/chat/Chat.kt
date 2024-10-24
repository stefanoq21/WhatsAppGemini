package com.stefanoq21.whatsappgemini.data.database.chat

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Chat(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
)

