package com.stefanoq21.whatsappgemini.presentation.screen.home.updates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stefanoq21.whatsappgemini.data.database.MyDatabaseRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class UpdatesViewModel(
    private val myDatabaseRepository: MyDatabaseRepository,
) : ViewModel() {

    val contact = myDatabaseRepository
        .getContact(0L)
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            null,
        )

}