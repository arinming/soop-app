package com.soop.designsystem

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SoopBottomSheet(
    content: @Composable () -> Unit,
    onDismissRequest: () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = {
            onDismissRequest()
        },
        containerColor = White,
    ) {
        content()
    }
}