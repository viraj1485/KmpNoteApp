package org.example.project.nativedatepicker

import androidx.compose.runtime.Composable

// commonMain
@Composable
expect fun PlatformDatePicker(
    show: Boolean,
    onDateSelected: (String) -> Unit,
    onDismiss: () -> Unit
)
