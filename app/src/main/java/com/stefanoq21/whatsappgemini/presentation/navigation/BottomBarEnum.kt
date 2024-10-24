package com.stefanoq21.whatsappgemini.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.PeopleAlt
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.icons.outlined.PeopleAlt
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import com.stefanoq21.whatsappgemini.R


enum class BottomBarEnum(
    val screen: HomeScreen,
    val title: Int,
    val icon: @Composable () -> Unit,
    val iconSelected: @Composable () -> Unit
) {
    Chats(screen = HomeScreen.Chats, title = R.string.bottom_bar_chats, icon = {
        Icon(
            modifier = Modifier.scale(0.9f),
            imageVector = Icons.AutoMirrored.Outlined.Chat,
            // painter = painterResource(id = R.drawable.test_icon),
            contentDescription = null,

            )
    }, iconSelected = {
        Icon(
            modifier = Modifier.scale(0.9f),
            imageVector = Icons.AutoMirrored.Filled.Chat,
            // painter = painterResource(id = R.drawable.test_icon),
            contentDescription = null,

            )
    }),

    Updates(screen = HomeScreen.Status, title = R.string.bottom_bar_updates, icon = {
        Icon(
            modifier = Modifier.scale(0.9f),
            imageVector = Icons.Outlined.Circle,
            // painter = painterResource(id = R.drawable.test_icon),
            contentDescription = null,

            )
    }, iconSelected = {
        Icon(
            modifier = Modifier.scale(0.9f),
            imageVector = Icons.Filled.Circle,
            // painter = painterResource(id = R.drawable.test_icon),
            contentDescription = null,

            )
    }),

    Communities(screen = HomeScreen.Community, title = R.string.bottom_bar_communities, icon = {
        Icon(
            modifier = Modifier.scale(0.9f),
            imageVector = Icons.Outlined.PeopleAlt,
            // painter = painterResource(id = R.drawable.test_icon),
            contentDescription = null,

            )
    }, iconSelected = {
        Icon(
            modifier = Modifier.scale(0.9f),
            imageVector = Icons.Filled.PeopleAlt,
            // painter = painterResource(id = R.drawable.test_icon),
            contentDescription = null,

            )
    }),

    Calls(screen = HomeScreen.Calls, title = R.string.bottom_bar_calls, icon = {
        Icon(
            modifier = Modifier.scale(0.9f),
            imageVector = Icons.Outlined.Call,
            // painter = painterResource(id = R.drawable.test_icon),
            contentDescription = null,

            )
    }, iconSelected = {
        Icon(
            modifier = Modifier.scale(0.9f),
            imageVector = Icons.Filled.Call,
            // painter = painterResource(id = R.drawable.test_icon),
            contentDescription = null,

            )
    })
}