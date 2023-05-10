package com.example.saygg.ui.uistate

import com.example.saygg.data.model.Tournament

sealed interface TournamentUiState{
    data class Success(val tournamentList: List<Tournament>) : TournamentUiState
    object Loading : TournamentUiState
    data class Error(val message: String) : TournamentUiState
}