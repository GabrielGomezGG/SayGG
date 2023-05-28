package com.example.saygg.tournament.ui

import com.example.saygg.profile.data.Player

sealed interface TournamentUiState{
    data class Success<T>(val values: T) : TournamentUiState
    object Loading : TournamentUiState
    data class Error(val message: String) : TournamentUiState
}