package com.stefanoq21.whatsappgemini.presentation.screen.home.chats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stefanoq21.whatsappgemini.data.database.MyDatabaseRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class ChatsViewModel(
    private val myDatabaseRepository: MyDatabaseRepository,
): ViewModel() {

    val chats = myDatabaseRepository
        .getChats()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            emptyList(),
        )

}