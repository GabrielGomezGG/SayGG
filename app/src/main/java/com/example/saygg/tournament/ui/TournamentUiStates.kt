package com.example.saygg.tournament.ui

sealed interface TournamentUiState{
    data class Success<T>(val values: T) : TournamentUiState
    object Loading : TournamentUiState
    data class Error(val message: String) : TournamentUiState
}