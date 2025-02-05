package com.example.game_list.ui.pages.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.game_list.data.model.Game
import com.example.game_list.domain.usescases.game_list.GameListUsesCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class GameListViewModel : ViewModel() {
    private val gameListUsesCase = GameListUsesCase()

    private val _games = MutableStateFlow<List<Game>>(emptyList())
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    init {
        fetchGames()
    }

    private fun fetchGames() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _games.value = gameListUsesCase.execute(null)
            } finally {
                _isLoading.value = false
            }
        }
    }

    val gameList = gameListUsesCase.filterGames(searchText, _games)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _games.value
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

}