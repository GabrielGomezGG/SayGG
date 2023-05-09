package com.example.saygg.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.saygg.data.model.Tournament
import com.example.saygg.ui.view.TournamentView
import com.example.saygg.ui.view.TournamentsThumbnail
import com.example.saygg.utils.Destinations.DTournamentView
import com.example.saygg.utils.Destinations.DTournaments

@Composable
fun NavigationHost(
    tournamentList: List<Tournament>,
    modifier: Modifier = Modifier
) {
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
                navTournamentView = {}
            )
        }
        composable(DTournamentView.route){
            TournamentView(
                imageProfile = "",
                imageBanner = "",
                title = "Titi",
                starAt = 0,
                endAt = 0,
                attenders = 0,
                venueAddress = ""
            )
        }
    }
}