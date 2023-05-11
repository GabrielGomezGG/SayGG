package com.example.saygg.ui.uistate

sealed interface TournamentUiState{
    data class Success<T>(val values: T) : TournamentUiState
    object Loading : TournamentUiState
    data class Error(val message: String) : TournamentUiState
}