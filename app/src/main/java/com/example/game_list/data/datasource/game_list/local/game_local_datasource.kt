package com.example.game_list.data.datasource.game_list.local
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.game_list.data.model.Game

@Dao
interface GameLocalDataSource {
    @Query("SELECT * FROM games")
    suspend fun getAllGames(): List<Game>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGames(games: List<Game>)
}