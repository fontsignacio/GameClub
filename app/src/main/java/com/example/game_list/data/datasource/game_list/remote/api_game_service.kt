package com.example.game_list.data.datasource.game_list.remote

import com.example.game_list.data.model.Game
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiGameService {
    @GET("/api/games")
    suspend fun getGames(@Query("category") category: String? = null) : List<Game>
}