package com.stefanoq21.whatsappgemini.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


@Composable
fun ContactIcon(icon: String) {
    AsyncImage(
        model = icon,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        placeholder = rememberVectorPainter(Icons.Filled.Face),
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(Color.LightGray),
    )
}
