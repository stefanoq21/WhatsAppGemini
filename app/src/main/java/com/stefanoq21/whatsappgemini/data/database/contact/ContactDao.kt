package com.stefanoq21.whatsappgemini.data.database.contact

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ContactDao {

    @Query("SELECT COUNT(id) FROM Contact")
    suspend fun count(): Int

    @Insert
    suspend fun insert(contact: Contact)

    @Query("SELECT * FROM Contact")
    suspend fun loadAll(): List<Contact>
}
