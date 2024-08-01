package com.example.game_list.ui.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.game_list.data.model.Game

@SuppressLint("SuspiciousIndentation")
@Composable
fun GameList() {
    val viewModel: GameListViewModel = viewModel()
    val games by viewModel.games.collectAsState()

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(games) { game ->
            GameItem(game)
        }
    }
}

@Composable
fun GameItem(game: Game) {
    Column(modifier = Modifier.padding(8.dp)) {
        Image(
            painter = rememberAsyncImagePainter(game.thumbnail),
            contentDescription = null,
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        BasicText(text = game.title ?: "")
        BasicText(text = game.shortDescription ?: "")
        BasicText(text = "Developer: ${game.developer}")
        BasicText(text = "Release Date: ${game.releaseDate}")
    }
}