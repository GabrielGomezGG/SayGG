package com.example.saygg

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.saygg.main.ui.MainScreen
import com.example.saygg.main.ui.MainViewModel
import com.example.saygg.tournament.ui.TournamentMainScreen
import com.example.saygg.tournament.ui.TournamentViewModel
import com.example.saygg.utils.Destinations.DTournamentView
import com.example.saygg.utils.Destinations.DTournaments


@Composable
fun NavigationHost(
    tournamentViewModel: TournamentViewModel,
    mainViewModel: MainViewModel,
) {

    val tournament by tournamentViewModel.tournament.observeAsState()
    val tournamentList by tournamentViewModel.tournamentList.observeAsState()

    val titleTop by mainViewModel.title.observeAsState("")
    val imageTop by mainViewModel.imageTitle.observeAsState("")

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = DTournaments.route,
    ) {
        composable(DTournaments.route) {
            MainScreen(
                tournamentList = tournamentList,
                navTournamentView = { id, name, images ->
                    var image = ""
                    images.map { if (it.type == "profile") image = it.url }
                    tournamentViewModel.getTournamentById(id)
                    mainViewModel.setTitle(name)
                    mainViewModel.setImage(image)
                    navController.navigate(DTournamentView.route)
                }
            )
        }
        composable(DTournamentView.route) {
            TournamentMainScreen(
                tournament = tournament,
                titleTop = titleTop,
                imageTop = imageTop
            ) {
                navController.navigate(DTournaments.route)
            }
        }
    }
}