package com.example.game_list.data.datasource.game_list

import com.example.game_list.data.model.Game
import retrofit2.http.GET

interface ApiGameService {
    @GET("/api/games")
    suspend fun getGames() : List<Game>
}