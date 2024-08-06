package com.example.game_list.data.repositories

import com.example.game_list.data.datasource.game_list.GameRemoteDataSourceImp
import com.example.game_list.data.model.Game
import com.example.game_list.domain.repositories.GameRepository

class GameRepositoryImp : GameRepository() {
    private val apiService = GameRemoteDataSourceImp.makeRetrofitSevice()

    override suspend fun fetchGames(): List<Game> {
        return apiService.getGames()
    }
}