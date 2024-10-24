package com.stefanoq21.whatsappgemini.data.database.contact

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    @PrimaryKey
    val id: Long,
    val name: String,
    val icon: String,
    val description: String,
)