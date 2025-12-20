package org.example.project.notes.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import assessmenttask.composeapp.generated.resources.Res
import assessmenttask.composeapp.generated.resources.add_note
import assessmenttask.composeapp.generated.resources.ic_check
import assessmenttask.composeapp.generated.resources.ic_datepicker
import assessmenttask.composeapp.generated.resources.ic_edit
import assessmenttask.composeapp.generated.resources.ic_info
import assessmenttask.composeapp.generated.resources.ic_keyboard
import assessmenttask.composeapp.generated.resources.ic_save
import assessmenttask.composeapp.generated.resources.ic_search
import assessmenttask.composeapp.generated.resources.note
import org.example.project.notes.resources.backgroundColor
import org.example.project.notes.resources.iconBackGroundColor
import org.example.project.notes.resources.nunito_medium
import org.example.project.notes.resources.nunito_regular
import org.example.project.notes.resources.textColor
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun NoteListTopBar(
    onSearchClick: () -> Unit,
    onInfoClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(
            horizontal = 16.dp
        ).background(backgroundColor),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(Res.string.note),
            fontFamily = nunito_medium(),
            fontSize = 40.sp,
            color = textColor
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = {
                onSearchClick()
            }, colors = IconButtonDefaults.iconButtonColors(
                containerColor = iconBackGroundColor
            ),
            shape = RoundedCornerShape(15.dp)
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_search),
                contentDescription = "",
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.width(15.dp))
        IconButton(
            onClick = {
                onInfoClick()
            }, colors = IconButtonDefaults.iconButtonColors(
                containerColor = iconBackGroundColor
            ),
            shape = RoundedCornerShape(15.dp)
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_info),
                contentDescription = "",
                tint = Color.White
            )
        }
    }
}

@Composable
fun AddNoteTopBar(
    showEditIcon:Boolean = false,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    onDateIconClick:() ->Unit,
    onEditIconClick:()->Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(
            horizontal = 16.dp
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                onBackClick()
            }, colors = IconButtonDefaults.iconButtonColors(
                containerColor = iconBackGroundColor
            ),
            shape = RoundedCornerShape(15.dp)
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_keyboard),
                contentDescription = "",
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = stringResource(Res.string.add_note),
            fontFamily = nunito_medium(),
            fontSize = 20.sp,
            color = textColor
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = {
                onDateIconClick()
            }, colors = IconButtonDefaults.iconButtonColors(
                containerColor = iconBackGroundColor
            ),
            shape = RoundedCornerShape(15.dp)
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_datepicker),
                contentDescription = "",
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.width(15.dp))
        if (showEditIcon){
            IconButton(
                onClick = {
                    onEditIconClick()
                }, colors = IconButtonDefaults.iconButtonColors(
                    containerColor = iconBackGroundColor
                ),
                shape = RoundedCornerShape(15.dp)
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_check),
                    contentDescription = "",
                    tint = Color.White
                )
            }
        }else{
            IconButton(
                onClick = {
                    onSaveClick()
                }, colors = IconButtonDefaults.iconButtonColors(
                    containerColor = iconBackGroundColor
                ),
                shape = RoundedCornerShape(15.dp)
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_save),
                    contentDescription = "",
                    tint = Color.White
                )
            }
        }
    }
}
