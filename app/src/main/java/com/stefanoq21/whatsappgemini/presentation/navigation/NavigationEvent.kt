package com.stefanoq21.whatsappgemini.presentation.navigation

import android.content.Context
import androidx.compose.material3.SnackbarDuration
import androidx.navigation.NavHostController

sealed interface NavigationEvent {
    data class OnSetContent(
        val activityNavController: NavHostController,
        val onBackPressed: () -> Unit
    ) : NavigationEvent

    data object OnBack : NavigationEvent
    data class OnNavigateToScreen(val screen: Screen) : NavigationEvent
    data class OnOpenUrl(val context: Context, val url: String) : NavigationEvent
    data class OnShowSnackBar(
        val message: String,
        val actionLabel: String? = null,
        val withDismissAction: Boolean = false,
        val duration: SnackbarDuration = SnackbarDuration.Short,
        val onDismiss: () -> Unit = {},
        val onPerformAction: () -> Unit = {}
    ) : NavigationEvent


    data class OnNavigateToChat(val chatId: Long) : NavigationEvent

}