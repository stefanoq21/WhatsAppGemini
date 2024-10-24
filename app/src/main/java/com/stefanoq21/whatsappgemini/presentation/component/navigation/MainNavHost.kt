package com.stefanoq21.whatsappgemini.presentation.component.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.stefanoq21.whatsappgemini.presentation.navigation.NavigationViewModel
import com.stefanoq21.whatsappgemini.presentation.navigation.Screen
import com.stefanoq21.whatsappgemini.presentation.screen.chat.ChatInitScreen
import com.stefanoq21.whatsappgemini.presentation.screen.home.HomeInitScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navigationViewModel: NavigationViewModel = koinViewModel(),
) {

    NavHost(
        navController = navigationViewModel.activityNavController,
        startDestination = Screen.Home,
        modifier = modifier
    ) {
        composable<Screen.Home> {
             HomeInitScreen()
        }

        composable<Screen.Chat> { backStackEntry ->
            val chatId = backStackEntry.toRoute<Screen.Chat>().chatId

            ChatInitScreen(
                chatId = chatId
            )
        }

        composable<Screen.AddChat> {
            // HomeInitScreen()
        }
    }
}

