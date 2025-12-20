package org.example.project.notes.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import assessmenttask.composeapp.generated.resources.Res
import assessmenttask.composeapp.generated.resources.ic_keyboard
import assessmenttask.composeapp.generated.resources.pdf_view
import org.example.project.commonwebview.pdfViewerWebView
import org.example.project.notes.resources.backgroundColor
import org.example.project.notes.resources.iconBackGroundColor
import org.example.project.notes.resources.nunito_medium
import org.example.project.notes.resources.textColor
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun PdfViewerScreen(
    onBackClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize().background(
            backgroundColor
        ).statusBarsPadding().navigationBarsPadding(),
        containerColor = backgroundColor,
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth().background(backgroundColor),
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
                    text = stringResource(Res.string.pdf_view),
                    fontFamily = nunito_medium(),
                    fontSize = 20.sp,
                    color = textColor
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(
                innerPadding
            )
        ) {
            pdfViewerWebView(
                url = "https://s24.q4cdn.com/216390268/files/doc_downloads/test.pdf"
            )
        }
    }
}