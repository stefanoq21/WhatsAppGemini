package com.stefanoq21.whatsappgemini.presentation.navigation

import androidx.navigation.NavBackStackEntry
import kotlinx.serialization.Serializable


sealed interface Screen {

    @Serializable
    data object Home : Screen

    @Serializable
    data object AddChat : Screen

    @Serializable
    data class Chat(val chatId: Long) : Screen


}
