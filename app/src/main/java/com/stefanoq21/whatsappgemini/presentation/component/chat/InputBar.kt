package com.stefanoq21.whatsappgemini.presentation.component.chat

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.TagFaces
import androidx.compose.material.icons.outlined.AttachFile
import androidx.compose.material.icons.outlined.KeyboardAlt
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.stefanoq21.whatsappgemini.R
import com.stefanoq21.whatsappgemini.presentation.theme.LocalExColorScheme

@Composable
fun InputBar(
    state: TextFieldState,
    onSendClick: () -> Unit,
    onCameraClick: () -> Unit,
    onPhotoPickerClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        color = LocalExColorScheme.current.extra.backgroundVariant,
    ) {
        Row(
            modifier = Modifier.padding(4.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Surface(
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colorScheme.background,
                shape = MaterialTheme.shapes.extraLarge,
                shadowElevation = 1.dp
            ) {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    val focusRequester = remember { FocusRequester() }
                    var keyboardType by remember { mutableStateOf(KeyboardType.Text) }

                    IconButton(onClick = {
                        focusRequester.requestFocus()
                        keyboardType = if (keyboardType == KeyboardType.Text) {
                            KeyboardType.Phone
                        } else {
                            KeyboardType.Text
                        }

                    }) {
                        Icon(
                            imageVector = if (keyboardType == KeyboardType.Text) Icons.Default.TagFaces else Icons.Outlined.KeyboardAlt,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }

                    Box(
                        modifier = Modifier.weight(1f),
                    ) {

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .alpha(if (state.text.isEmpty()) 1f else 0f),
                            style = MaterialTheme.typography.bodyLarge,
                            text = stringResource(R.string.message_placeholder),
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )

                        BasicTextField(
                            state = state,
                            modifier = Modifier
                                .fillMaxWidth()
                                .focusRequester(focusRequester),
                            lineLimits = TextFieldLineLimits.MultiLine(maxHeightInLines = 6),
                            keyboardOptions = KeyboardOptions(
                                capitalization = KeyboardCapitalization.Sentences,
                                imeAction = ImeAction.Send,
                                keyboardType = keyboardType,
                            ),
                            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                            onKeyboardAction = { onSendClick() },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onBackground)
                        )


                    }

                    IconButton(onClick = onPhotoPickerClick) {
                        Icon(
                            imageVector = Icons.Outlined.AttachFile,
                            contentDescription = "Select Photo or video",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }

                    AnimatedVisibility(visible = state.text.isEmpty()) {
                        IconButton(onClick = onCameraClick) {
                            Icon(
                                imageVector = Icons.Outlined.PhotoCamera,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                    }


                }
            }

            if (state.text.isEmpty()) {
                FilledIconButton(
                    onClick = {},
                    modifier = Modifier.size(48.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.Mic,
                        contentDescription = null,
                    )


                }
            } else {
                FilledIconButton(
                    onClick = onSendClick,
                    modifier = Modifier.size(48.dp),
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = null,
                    )
                }
            }


        }
    }
}