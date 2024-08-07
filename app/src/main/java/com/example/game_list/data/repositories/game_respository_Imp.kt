package com.example.game_list.data.repositories

import com.example.game_list.data.datasource.game_list.local.GameDatabase
import com.example.game_list.data.datasource.game_list.remote.GameRemoteDataSourceImp
import com.example.game_list.data.model.Game
import com.example.game_list.domain.repositories.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GameRepositoryImp : GameRepository() {
    private val gameLocalDataSource = GameDatabase.getInstance()?.gameDao()
    private val gameRemoteDataSourceImp = GameRemoteDataSourceImp.makeRetrofitSevice()

    override suspend fun fetchGames(): List<Game> {
        return withContext(Dispatchers.IO) {
            val localGames = gameLocalDataSource?.getAllGames()
            val remoteGames = gameRemoteDataSourceImp.getGames()
            localGames?.ifEmpty {
                gameLocalDataSource?.insertGames(remoteGames)
                remoteGames
            } ?: remoteGames
        }
    }
}