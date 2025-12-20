package org.example.project.notes.screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.database.NoteEntity
import org.example.project.notes.resources.cardBackGroundColor
import org.example.project.notes.resources.cardBorderColor
import org.example.project.notes.resources.nunito_bold
import org.example.project.notes.resources.textColor

@Composable
fun NoteItem(
    note: NoteEntity,
    onSubtitleClick: (String) -> Unit,
    onLongPress: (id: Int) -> Unit,
    onClick:(id:Int) ->Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(
            horizontal = 16.dp
        ).pointerInput(Unit) {
            detectTapGestures(
                onTap = {
                  onClick(note.id)
                },
                onLongPress = {
                    onLongPress(note.id)
                }
            )
        },
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = cardBackGroundColor,
        ),
        border = BorderStroke(2.dp, cardBorderColor)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(
                vertical = 10.dp,
                horizontal = 16.dp
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = note.title,
                    fontFamily = nunito_bold(),
                    fontSize = 30.sp,
                    color = textColor
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = note.subtitle,
                    fontFamily = nunito_bold(),
                    fontSize = 22.sp,
                    color = textColor,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.clickable {
                        onSubtitleClick(note.subtitle)
                    }
                )
            }
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                text = note.date,
                fontFamily = nunito_bold(),
                fontSize = 12.sp,
                color = textColor,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
            )

        }

    }
}