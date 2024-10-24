@file:OptIn(ExperimentalMaterial3Api::class)

package com.stefanoq21.whatsappgemini.presentation.component.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.stefanoq21.whatsappgemini.R
import com.stefanoq21.whatsappgemini.presentation.navigation.BottomBarEnum

@Composable
fun HomeTopBar(
    element: BottomBarEnum,
) {
    Column {
        TopAppBar(
            modifier = Modifier,
            /*colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.onBackground,
            ),*/
            title = {
                when (element) {
                    BottomBarEnum.Chats ->
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = stringResource(id = R.string.top_bar_chat),
                            color = MaterialTheme.colorScheme.primary
                        )

                    BottomBarEnum.Updates ->
                        Text(text = stringResource(id = R.string.top_bar_updates))

                    BottomBarEnum.Communities ->
                        Text(text = stringResource(id = R.string.top_bar_communities))

                    BottomBarEnum.Calls ->
                        Text(text = stringResource(id = R.string.top_bar_calls))
                }

            },
            actions = {
                IconButton(onClick = { /* do something */ }) {
                    Icon(
                        imageVector = Icons.Outlined.CameraAlt,
                        contentDescription = "Localized description"
                    )
                }
                if (element != BottomBarEnum.Communities)
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "Localized description"
                        )
                    }
                IconButton(onClick = { /* do something */ }) {
                    Icon(
                        imageVector = Icons.Outlined.MoreVert,
                        contentDescription = "Localized description"
                    )
                }


            }


        )
        HorizontalDivider(
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f),
            modifier = Modifier
                .height(0.3.dp)
                .fillMaxWidth()
        )
    }

}