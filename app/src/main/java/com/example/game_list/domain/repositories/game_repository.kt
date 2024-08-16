package com.example.game_list.domain.repositories

import com.example.game_list.data.model.Game

abstract class GameRepository {
    abstract suspend fun fetchGames(category: String?): List<Game>
}