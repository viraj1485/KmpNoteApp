package org.example.project.notes.screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import org.example.project.notes.resources.nunito_regular
import org.example.project.notes.resources.textColor

@Composable
fun NoteTextField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    hintText: String,
    fontSize: TextUnit = 14.sp,
    fontFamily: FontFamily = nunito_regular(),
    maxLines: Int = 2,
    hintTextColor: Color = Color.Gray
) {
    BasicTextField(
        value = text,
        onValueChange = onValueChange,
        textStyle = TextStyle(
            fontFamily = fontFamily,
            fontSize = fontSize, color = textColor
        ),
        decorationBox = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                if (text.isEmpty()) {
                    Text(
                        text = hintText,
                        fontSize = fontSize,
                        color = hintTextColor
                    )
                }
                it()
            }
        },
        cursorBrush = SolidColor(textColor),
        modifier = modifier.fillMaxWidth(),
        maxLines = maxLines
    )
}