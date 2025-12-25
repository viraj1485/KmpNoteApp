package com.example.assessmenttask.apisetup.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import assessmenttask.composeapp.generated.resources.Res
import assessmenttask.composeapp.generated.resources.ic_keyboard
import com.example.assessmenttask.apisetup.data.TodoResponseItem
import com.example.assessmenttask.apisetup.viewmodel.MainViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import org.example.project.notes.resources.backgroundColor
import org.example.project.notes.resources.cardBackGroundColor
import org.example.project.notes.resources.cardBorderColor
import org.example.project.notes.resources.nunito_bold
import org.example.project.notes.resources.textColor
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalMaterial3Api::class, FlowPreview::class)
@Composable
fun TodoListScreen(
    viewModel: MainViewModel,
) {
    val scroll_position by viewModel.scroll_position.collectAsStateWithLifecycle()
    val lazyListState = rememberLazyListState(
        initialFirstVisibleItemIndex = scroll_position
    )
    val todos by viewModel.todoList.collectAsStateWithLifecycle()
    LaunchedEffect(scroll_position) {
        if (lazyListState.firstVisibleItemIndex != scroll_position) {
            lazyListState.scrollToItem(scroll_position)
        }
    }


    LaunchedEffect(lazyListState) {
        snapshotFlow {
            lazyListState.firstVisibleItemIndex
        }.debounce(500L).collectLatest {
            viewModel.updatePosition(it)
        }
    }


    Scaffold(
        modifier = Modifier.fillMaxSize().background(
            backgroundColor
        ).statusBarsPadding().navigationBarsPadding(),
        containerColor = backgroundColor,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Todo List Screen",
                        color = textColor,
                        fontSize = 26.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = backgroundColor
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {

                        },
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_keyboard),
                            contentDescription = "", tint = textColor
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        LazyColumn(
            state = lazyListState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = innerPadding,
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(todos) { todo ->
                TodoItem(todo)
            }
        }
    }
}


@Composable
fun TodoItem(
    todo: TodoResponseItem,
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(
            horizontal = 16.dp
        ),
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
                    text = todo.title,
                    fontFamily = nunito_bold(),
                    fontSize = 30.sp,
                    color = textColor
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = todo.userId.toString(),
                    fontFamily = nunito_bold(),
                    fontSize = 22.sp,
                    color = textColor,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                text = todo.userId.toString(),
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
