package com.stefanoq21.whatsappgemini.presentation.component.chat

import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@Composable
fun VideoMessagePreview(videoUri: String, onClick: () -> Unit) {
    val context = LocalContext.current.applicationContext
    val bitmapState = produceState<Bitmap?>(initialValue = null) {
        withContext(Dispatchers.IO) {
            val mediaMetadataRetriever = MediaMetadataRetriever()
            mediaMetadataRetriever.setDataSource(context, Uri.parse(videoUri))
            value = mediaMetadataRetriever.frameAtTime
        }
    }

    bitmapState.value?.let { bitmap ->
        Box(
            modifier = Modifier
                .clickable(onClick = onClick)
                .padding(10.dp),
        ) {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Gray, BlendMode.Darken),
            )

            Icon(
                Icons.Filled.PlayArrow,
                tint = Color.White,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.Center)
                    .border(3.dp, Color.White, shape = CircleShape),
            )
        }
    }
}


