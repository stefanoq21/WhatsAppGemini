package com.stefanoq21.whatsappgemini.domain.di

import com.stefanoq21.whatsappgemini.data.ChatRepository
import com.stefanoq21.whatsappgemini.data.database.MyDatabase
import com.stefanoq21.whatsappgemini.data.database.MyDatabaseRepository
import com.stefanoq21.whatsappgemini.presentation.navigation.NavigationViewModel
import com.stefanoq21.whatsappgemini.presentation.screen.chat.ChatViewModel
import com.stefanoq21.whatsappgemini.presentation.screen.home.chats.ChatsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val myModules = module {
    single { MyDatabase.getInstance(get()) }
    factory { MyDatabaseRepository(get(), get()) }
    factory { ChatRepository(get(), get()) }

    viewModel {
        NavigationViewModel()
    }
    viewModel {
        ChatsViewModel(get())
    }
    viewModel {
        ChatViewModel(get(), get())
    }


}