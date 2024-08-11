package com.example.game_list.ui.pages.home

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.game_list.data.model.Game
import com.example.game_list.ui.theme.blueLight

@Composable
fun HomePage(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(blueLight),
        horizontalAlignment = Alignment.CenterHorizontally
    ) { GameList() }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun GameList() {
    val viewModel: GameListViewModel = viewModel()
    val games by viewModel.gameList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(userScrollEnabled = true, modifier = Modifier.padding(16.dp)) {
            item {
                SearchBarCompose(viewModel)
                Spacer(modifier = Modifier.height(16.dp))
                BasicText("Free-To-Play Games",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            items(games) { game ->
                GameItem(game)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun GameItem(game: Game) {
    Card {
        Column(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = rememberAsyncImagePainter(game.thumbnail),
                contentDescription = null,
                modifier = Modifier
                    .height(190.dp)
                    .fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BasicText(
                    text = game.title ?: "",
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                BasicText(
                    text = "${game.platform}",
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .align(Alignment.CenterVertically)
                )

            }
            Spacer(modifier = Modifier.height(8.dp))
            BasicText(
                text = "${game.short_description}",
                overflow = TextOverflow.Ellipsis,

            )
        }
    }
}

@Composable
fun SearchBarCompose(viewModel: GameListViewModel) {
    val searchText by viewModel.searchText.collectAsState()
    TextField(
        value = searchText,
        onValueChange = viewModel::onSearchTextChange,
        modifier = Modifier
            .fillMaxWidth()
            .border(
                BorderStroke(width = 2.dp, color = Color.White),
                shape = RoundedCornerShape(50.dp)
            )
            .height(50.dp),
        textStyle = TextStyle(fontSize = 17.sp),
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
    )
}