package com.example.watchbase.ui.screens.designsystem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    isNavigateUpAvailable: Boolean,
    onNavigateUp: () -> Unit = {},
    onClickClose: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = if (isNavigateUpAvailable) Arrangement.SpaceBetween else Arrangement.End
    ) {
        if (isNavigateUpAvailable) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                tint = Color.White,
                contentDescription = "Navigate Up",
                modifier = modifier.clickable { onNavigateUp() })
        }
        Icon(
            imageVector = Icons.Default.Close,
            tint = Color.White,
            contentDescription = "Close",
            modifier = modifier.clickable { onClickClose() })
    }
}