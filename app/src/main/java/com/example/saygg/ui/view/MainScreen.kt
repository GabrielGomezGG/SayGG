package com.example.saygg.ui.view

import android.util.Log
import androidx.compose.material3.CircularProgressIndicator
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
            CircularProgressIndicator()
        }
        is TournamentUiState.Success -> {
            (tournaments as TournamentUiState.Success).todos.forEach {
                Log.d("titi",it.name)
            }
        }
        else -> {}
    }
}