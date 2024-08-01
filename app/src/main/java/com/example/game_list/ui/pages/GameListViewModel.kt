package com.example.game_list.ui.pages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.game_list.data.model.Game
import com.example.game_list.domain.RepositoryGame
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class GameListViewModel : ViewModel() {
    private val repository = RepositoryGame()

    private val _games = MutableStateFlow<List<Game>>(emptyList())
    val games: StateFlow<List<Game>> = _games

    init {
        fetchGames()
    }

    private fun fetchGames() {
        viewModelScope.launch {
            try {
                val fetchedGames = repository.fetchGames()
                _games.value = fetchedGames
                println("Fetched Games: ${_games.value}")
                println("Holis $games")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}