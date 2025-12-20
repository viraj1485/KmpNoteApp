package org.example.project.notes.screen.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import assessmenttask.composeapp.generated.resources.Res
import assessmenttask.composeapp.generated.resources.ic_add
import org.example.project.notes.resources.iconBackGroundColor
import org.jetbrains.compose.resources.painterResource

@Composable
fun addButton(
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = {
            onClick()
        },
        shape = RoundedCornerShape(15.dp),
        containerColor = iconBackGroundColor,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 2.dp
        )
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_add),
            contentDescription = "",
            tint = Color.White
        )
    }
}