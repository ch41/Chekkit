package com.example.design.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ChekkitDimensions(
    val paddingSmall: Dp = 8.dp,
    val paddingMedium: Dp = 16.dp,
    val paddingLarge: Dp = 24.dp,
    val screenPadding: Dp = 20.dp,
    
    val cardCornerRadius: Dp = 24.dp,
    val iconSizeSmall: Dp = 14.dp,
    val iconSizeMedium: Dp = 24.dp,
    val avatarSize: Dp = 48.dp,
    
    val fontCaption: TextUnit = 12.sp,
    val fontBody: TextUnit = 14.sp,
    val fontTitle: TextUnit = 18.sp,
    val fontHeader: TextUnit = 24.sp,
    val fontDisplay: TextUnit = 40.sp
)

val LocalDimensions = compositionLocalOf { ChekkitDimensions() }
