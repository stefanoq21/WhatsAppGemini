package com.stefanoq21.whatsappgemini.data.database.contact

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Query("SELECT COUNT(id) FROM Contact")
    suspend fun count(): Int

    @Insert
    suspend fun insert(contact: Contact)


    @Query("SELECT * FROM Contact WHERE id = :id")
    fun contactById(id: Long): Flow<Contact?>


    @Query("SELECT * FROM Contact")
    suspend fun loadAll(): List<Contact>
}
