package com.stefanoq21.whatsappgemini.presentation.navigation

import kotlinx.serialization.Serializable


sealed interface HomeScreen {
    @Serializable
    data object Chats : HomeScreen

    @Serializable
    data object Status : HomeScreen

    @Serializable
    data object Community : HomeScreen

    @Serializable
    data object Calls : HomeScreen
}
