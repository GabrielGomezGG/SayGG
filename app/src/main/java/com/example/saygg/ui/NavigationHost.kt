package com.example.saygg.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.saygg.data.model.Tournament
import com.example.saygg.ui.view.TournamentView
import com.example.saygg.ui.view.TournamentsThumbnail
import com.example.saygg.ui.viewmodel.TournamentViewModel
import com.example.saygg.utils.Destinations.DTournamentView
import com.example.saygg.utils.Destinations.DTournaments

@Composable
fun NavigationHost(
    tournamentList: List<Tournament>,
    modifier: Modifier = Modifier,
    tournamentViewModel: TournamentViewModel
) {

    val tournament by tournamentViewModel.tournament.observeAsState()

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = DTournaments.route,
        modifier = modifier
    ){
        composable(DTournaments.route){
            TournamentsThumbnail(
                tournamentList = tournamentList,
                modifier = modifier,
                navTournamentView = {
                    tournamentViewModel.getTournamentById(it)
                    navController.navigate(DTournamentView.route)
                }
            )
        }
        composable(DTournamentView.route){
            TournamentView(
                tournament ?: Tournament()
            )
        }
    }
}