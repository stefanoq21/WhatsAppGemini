package com.stefanoq21.whatsappgemini.presentation.component.chat

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stefanoq21.whatsappgemini.presentation.component.ContactIcon
import com.stefanoq21.whatsappgemini.presentation.screen.chat.HeaderInfo

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ChatTopBar(
    headerInfo: HeaderInfo?,
    onBackPressed: (() -> Unit)?,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                //horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                if (headerInfo?.icon != null) {
                    ContactIcon(headerInfo.icon)
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = headerInfo.text,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        },
        modifier = modifier,
        navigationIcon = {
            if (onBackPressed != null) {
                Icon(
                    modifier = Modifier.combinedClickable(
                        onClick = onBackPressed,
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ),
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                )
            }
        },
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ChatTopBarPreview() {
    ChatTopBar(
        headerInfo = HeaderInfo(
            text = "John Doe",
            icon = "example_icon_url",
            isGroup = false
        ),
        onBackPressed = { }
    )
}