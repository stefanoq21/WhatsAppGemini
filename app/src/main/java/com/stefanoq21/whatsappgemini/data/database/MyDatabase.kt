package com.stefanoq21.whatsappgemini.data.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.stefanoq21.whatsappgemini.R
import com.stefanoq21.whatsappgemini.data.Constants
import com.stefanoq21.whatsappgemini.data.database.chat.Chat
import com.stefanoq21.whatsappgemini.data.database.chat.ChatAttendee
import com.stefanoq21.whatsappgemini.data.database.chat.ChatDao
import com.stefanoq21.whatsappgemini.data.database.chat.ChatWithLastMessage
import com.stefanoq21.whatsappgemini.data.database.contact.Contact
import com.stefanoq21.whatsappgemini.data.database.contact.ContactDao
import com.stefanoq21.whatsappgemini.data.database.message.Message
import com.stefanoq21.whatsappgemini.data.database.message.MessageDao

@Database(
    entities = [
        Contact::class,
        Chat::class,
        ChatAttendee::class,
        Message::class,
    ],
    views = [ChatWithLastMessage::class],
    version = 1,
    exportSchema = true,
)
abstract class MyDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDao
    abstract fun chatDao(): ChatDao
    abstract fun messageDao(): MessageDao

    companion object {

        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    Constants.databaseName
                ).addCallback(
                    object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            db.populateInitialData(context)
                        }
                    },
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        fun SupportSQLiteDatabase.populateInitialData(context: Context) {

            insert(
                table = "contact",
                conflictAlgorithm = SQLiteDatabase.CONFLICT_NONE,
                values = ContentValues().apply {
                    put("id", 0L)
                    put("icon", "file:///android_asset/you.jpg")
                    put("name", "You")
                    put("description", "")
                },
            )

            // Populate data for other contacts
            val contacts = listOf(
                Contact(
                    1L,
                    "Mom",
                    "file:///android_asset/mom.jpg",
                    context.getString(R.string.mom_description)
                ),
                Contact(
                    2L,
                    "Dad",
                    "file:///android_asset/dad.jpg",
                    context.getString(R.string.dad_description)
                ),
                Contact(
                    3L,
                    "Uncle John",
                    "file:///android_asset/uncle_john.jpg",
                    context.getString(R.string.uncle_john_description)
                )
            )
            val chatIds = contacts.map { it.id }

            contacts.forEachIndexed { index, contact ->
                // Insert contact
                insert(
                    table = "Contact",
                    conflictAlgorithm = SQLiteDatabase.CONFLICT_IGNORE,
                    values = ContentValues().apply {
                        put("id", contact.id)
                        put("icon", contact.icon)
                        put("name", contact.name)
                        put("description", contact.description)
                    },
                )

                // Insert chat id
                insert(
                    table = "Chat",
                    conflictAlgorithm = SQLiteDatabase.CONFLICT_IGNORE,
                    values = ContentValues().apply {
                        put("id", chatIds[index])
                    },
                )

                // Insert chat attendee
                insert(
                    table = "ChatAttendee",
                    conflictAlgorithm = SQLiteDatabase.CONFLICT_IGNORE,
                    values = ContentValues().apply {
                        put("chatId", chatIds[index])
                        put("attendeeId", contact.id)
                    },
                )
            }
        }


    }

}