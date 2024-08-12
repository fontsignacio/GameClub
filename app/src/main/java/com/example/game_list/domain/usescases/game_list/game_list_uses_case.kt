package com.example.game_list.domain.usescases.game_list

import com.example.game_list.data.model.Game
import com.example.game_list.data.repositories.GameRepositoryImp
import com.example.game_list.domain.usescases.BaseUsesCases
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GameListUsesCase : BaseUsesCases<GameRepositoryImp, List<Game>>() {

    override suspend fun execute(input: GameRepositoryImp): List<Game> {
        return try {
            input.fetchGames()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    fun filterGames(searchText: Flow<String>, games: Flow<List<Game>>): Flow<List<Game>> {
        return searchText.combine(games) { text, gameList ->
            if (text.isBlank()) {
                gameList
            } else {
                gameList.filter { game ->
                    game.title?.trim()?.contains(text.trim(), ignoreCase = true) == true
                }
            }
        }
    }
}