package com.example.game_list.data.datasourse

import com.example.game_list.data.model.Game
import retrofit2.http.GET

interface ApiService {
    @GET("/api/games")
    suspend fun getGames() : List<Game>
}