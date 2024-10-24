package com.stefanoq21.whatsappgemini.data

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.Content
import com.google.ai.client.generativeai.type.content
import com.stefanoq21.whatsappgemini.BuildConfig
import com.stefanoq21.whatsappgemini.R
import com.stefanoq21.whatsappgemini.data.database.MyDatabaseRepository
import com.stefanoq21.whatsappgemini.data.database.contact.Contact
import com.stefanoq21.whatsappgemini.presentation.screen.chat.ChatMessage

class ChatRepository(
    private val myDatabaseRepository: MyDatabaseRepository,
    private val context: Context,
) {

    suspend fun sendMessage(
        chatId: Long,
        text: String,
        mediaUri: String?,
        mediaMimeType: String?,
        contact: Contact?,
        messages: List<ChatMessage>,
    ) {

        // Save the message to the database
        myDatabaseRepository.insertMessage(chatId, text, 0L, mediaUri, mediaMimeType)

        // Create a generative AI Model to interact with the Gemini API.
        val generativeModel = GenerativeModel(
            modelName = "gemini-1.5-flash",
            // Set your Gemini API in as an `API_KEY` variable in your local.properties file
            apiKey = BuildConfig.API_KEY,
            // Set a system instruction to set the behavior of the model.
            systemInstruction = content {
                text("Please respond to this chat conversation like a friendly ${contact?.description?: "person"}.")
            },
        )


        if (BuildConfig.API_KEY != "DUMMY_API_KEY") {
            // Get the previous messages and them generative model chat
            val pastMessages = createMessageHistory(messages)
            val chat = generativeModel.startChat(
                history = pastMessages,
            )

            // Send a message prompt to the model to generate a response
            var response = try {
                if (mediaMimeType?.contains("image") == true) {
                    context.contentResolver.openInputStream(
                        Uri.parse(mediaUri),
                    ).use {
                        if (it != null) {
                            chat.sendMessage(BitmapFactory.decodeStream(it)).text?.trim() ?: "..."
                        } else {
                            context.getString(
                                R.string.image_error,
                            )
                        }
                    }
                } else {
                    chat.sendMessage(text).text?.trim() ?: "..."
                }
            } catch (e: Exception) {
                e.printStackTrace()
                context.getString(
                    R.string.gemini_error,
                    e.message ?: context.getString(R.string.unknown_error),
                )
            }

            // Save the generated response to the database
            myDatabaseRepository.insertMessage(
                chatId,
                response,
                contact?.id?: 0L,
                null,
                null,
            )
        } else {
            // Receive a reply.
            myDatabaseRepository.insertMessage(
                chatId,
                context.getString(
                    R.string.gemini_key_error
                ) + " local",
                contact?.id?: 0L,
                null,
                null,
            )
        }
    }


    private fun createMessageHistory(messages: List<ChatMessage>): List<Content> {

        val pastContents = messages.map { message: ChatMessage ->
            val role = if (message.fromMe) "user" else "model"
            return@map content(role = role) { text(message.text) }
        }
        return pastContents
    }


}