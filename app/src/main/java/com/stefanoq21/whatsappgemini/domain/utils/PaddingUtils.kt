package com.stefanoq21.whatsappgemini.domain.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection

object PaddingUtils {


    fun PaddingValues.copy(
        layoutDirection: LayoutDirection,
        start: Dp? = null,
        top: Dp? = null,
        end: Dp? = null,
        bottom: Dp? = null,
    ) = PaddingValues(
        start = start ?: calculateStartPadding(layoutDirection),
        top = top ?: calculateTopPadding(),
        end = end ?: calculateEndPadding(layoutDirection),
        bottom = bottom ?: calculateBottomPadding(),
    )

}