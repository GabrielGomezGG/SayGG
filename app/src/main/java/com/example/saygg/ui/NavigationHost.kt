package com.example.saygg.ui

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.saygg.R
import com.example.saygg.ui.view.TournamentView
import com.example.saygg.ui.view.TournamentsThumbnail
import com.example.saygg.ui.viewmodel.MainViewModel
import com.example.saygg.ui.viewmodel.TournamentViewModel
import com.example.saygg.utils.Destinations.DTournamentView
import com.example.saygg.utils.Destinations.DTournaments

@Composable
fun NavigationHost(
    modifier: Modifier = Modifier,
    tournamentViewModel: TournamentViewModel,
    mainViewModel: MainViewModel,
) {

    val tournament by tournamentViewModel.tournament.observeAsState()
    val tournamentList by tournamentViewModel.tournamentList.observeAsState()

    val navController = rememberNavController()

    val tournamentsText = stringResource(R.string.tournaments)

    NavHost(
        navController = navController,
        startDestination = DTournaments.route,
    ){
        composable(DTournaments.route){
            TournamentsThumbnail(
                tournaments = tournamentList,
                modifier = modifier,
                navTournamentView = { id, name ->
                    tournamentViewModel.getTournamentById(id)
                    mainViewModel.setTitle(name)
                    navController.navigate(DTournamentView.route)
                }
            )
        }
        composable(DTournamentView.route){
            TournamentView(
                tournament,
                modifier
            ){
                mainViewModel.setTitle(tournamentsText)
                navController.navigate(DTournaments.route)
            }
        }
    }
}