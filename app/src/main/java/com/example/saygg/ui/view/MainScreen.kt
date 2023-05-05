package com.example.saygg.ui.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.saygg.R
import com.example.saygg.ui.viewmodel.TournamentUiState
import com.example.saygg.ui.viewmodel.TournamentViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(tournamentViewModel: TournamentViewModel) {
    val tournaments by tournamentViewModel.tournamentList.observeAsState()

    Scaffold(
        topBar = { MainTopAppBar(title = "Tournaments") }
    ) {

        when (tournaments) {
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


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(title: String) {
    TopAppBar(
        title = {
            Box(modifier = Modifier.padding(8.dp)) {
                IconButton(
                    onClick = { /*TODO*/ },
                    Modifier.size(35.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.startgglogo),
                        contentDescription = null,
                    )
                }
                Text(
                    text = title,
                    Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        },
        navigationIcon = {
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = null,
                    Modifier.size(80.dp)
                )
            }
        }
    )
}

