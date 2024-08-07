package com.example.game_list.data.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class Game(
    @PrimaryKey val id: Int,
    @ColumnInfo val title: String?,
    @ColumnInfo val thumbnail: String?,
    @ColumnInfo val short_description: String?,
    @ColumnInfo val game_url: String?,
    @ColumnInfo val genre: String?,
    @ColumnInfo val platform: String?,
    @ColumnInfo val publisher: String?,
    @ColumnInfo val developer: String?,
    @ColumnInfo val release_date: String?,
    @ColumnInfo val freetogame_profile_url: String?
)