package com.example.game_list.ui.pages.category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.game_list.enums.GameGenre
import com.example.game_list.ui.theme.blueLight

@Composable
fun CategoryPage(paddingValues: PaddingValues){
    val genres = GameGenre.entries.toList()

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(blueLight),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(8.dp)
        ) {
            items(genres) { genre ->
                GameGenreItem(genre)
            }
        }
    }
}

@Composable
fun GameGenreItem(genre: GameGenre) {
    Card(modifier = Modifier.padding(10.dp)) {
        Text(
            text = genre.displayName,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}