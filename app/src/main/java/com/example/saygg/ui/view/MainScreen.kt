package com.example.saygg.ui.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.saygg.ui.viewmodel.TournamentUiState
import com.example.saygg.ui.viewmodel.TournamentViewModel

@Composable
fun MainScreen(tournamentViewModel: TournamentViewModel) {
    val tournaments by tournamentViewModel.tournamentList.observeAsState()

    when(tournaments){
        is TournamentUiState.Error -> {
            Text(text = (tournaments as TournamentUiState.Error).message)
        }
        is TournamentUiState.Loading -> {

        }
        is TournamentUiState.Success -> {
            TournamentsView(tournamentList = (tournaments as TournamentUiState.Success).tournamentList)
        }
        else -> {}
    }
}

