package com.stefanoq21.whatsappgemini.presentation.navigation

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

class NavigationViewModel : ViewModel() {
    private lateinit var onBackPressed: () -> Unit
    lateinit var activityNavController: NavHostController
        private set

    val snackBarHostState = SnackbarHostState()

    private fun showSnackBar(
        message: String,
        actionLabel: String? = null,
        withDismissAction: Boolean = false,
        duration: SnackbarDuration = SnackbarDuration.Short,
        onDismiss: () -> Unit = {},
        onPerformAction: () -> Unit = {}
    ) {
        viewModelScope.launch {
            val snackBarResult = snackBarHostState.showSnackbar(
                message = message,
                actionLabel = actionLabel,
                withDismissAction = withDismissAction,
                duration = duration
            )
            when (snackBarResult) {
                SnackbarResult.Dismissed -> onDismiss()
                SnackbarResult.ActionPerformed -> onPerformAction()
            }
        }
    }

    fun onEvent(event: NavigationEvent) {
        when (event) {
            is NavigationEvent.OnSetContent -> {
                activityNavController = event.activityNavController
                onBackPressed = event.onBackPressed
            }

            is NavigationEvent.OnBack -> onBackPressed()


            is NavigationEvent.OnNavigateToScreen -> {
                activityNavController.navigate(event.screen)
            }

            is NavigationEvent.OnShowSnackBar -> {
                showSnackBar(
                    message = event.message,
                    actionLabel = event.actionLabel,
                    withDismissAction = event.withDismissAction,
                    duration = event.duration,
                    onDismiss = event.onDismiss,
                    onPerformAction = event.onPerformAction
                )
            }

            is NavigationEvent.OnOpenUrl -> {
                openCustomTab(event.context, event.url)
            }

            is NavigationEvent.OnNavigateToChat -> {
                navigateToChatScreen(event.chatId)
            }
        }
    }


    private fun navigateToChatScreen(
        chatId: Long
    ) {
        activityNavController.navigate(Screen.Chat(chatId))
    }

    private fun openCustomTab(context: Context, url: String) {
        val builder: CustomTabsIntent.Builder = CustomTabsIntent.Builder()
        val customTabsIntent: CustomTabsIntent = builder.build()
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }


}