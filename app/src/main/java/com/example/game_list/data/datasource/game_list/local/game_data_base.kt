package com.example.game_list.data.datasource.game_list.local

import android.content.Context
import androidx.compose.runtime.rememberCompositionContext
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.game_list.data.model.Game

@Database(entities = [Game::class], version = 1)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameLocalDataSource

    companion object {
        @Volatile private var instance: GameDatabase? = null

        fun initDataBase(context: Context): GameDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    GameDatabase::class.java,
                    "game-db"
                ).build().also { instance = it }
            }

        fun getInstance(): GameDatabase? {
            return instance
        }
    }
}