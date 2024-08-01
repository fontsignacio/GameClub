package com.example.game_list.domain

import com.example.game_list.data.datasourse.RetrofitInstance
import com.example.game_list.data.model.Game

class RepositoryGame{
    private val apiService = RetrofitInstance.makeRetrofitSevice()

    suspend fun fetchGames(): List<Game> {
        return apiService.getGames()
    }
}