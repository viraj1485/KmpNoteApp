package org.example.project.notes.resources

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import assessmenttask.composeapp.generated.resources.Res
import assessmenttask.composeapp.generated.resources.nunito_bold
import assessmenttask.composeapp.generated.resources.nunito_medium
import assessmenttask.composeapp.generated.resources.nunito_regular
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font



@OptIn(ExperimentalResourceApi::class)
@Composable
fun nunito_regular() = FontFamily(
    Font(Res.font.nunito_regular, weight = FontWeight.Light),
)

@Composable
fun nunito_medium() = FontFamily(
    Font(Res.font.nunito_medium, weight = FontWeight.Medium),
)

@Composable
fun nunito_bold() = FontFamily(
    Font(Res.font.nunito_bold, weight = FontWeight.Bold),
)
