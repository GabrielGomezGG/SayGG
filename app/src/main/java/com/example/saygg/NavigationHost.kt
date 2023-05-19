package com.example.saygg

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.saygg.tournament.view.TournamentView
import com.example.saygg.tournament.view.TournamentsThumbnail
import com.example.saygg.main.viewmodel.MainViewModel
import com.example.saygg.tournament.viewmodel.TournamentViewModel
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
                navTournamentView = { id, name, images ->
                    var image = ""
                    images.map { if(it.type == "profile") image = it.url  }
                    tournamentViewModel.getTournamentById(id)
                    mainViewModel.setTitle(name)
                    mainViewModel.setImage(image)
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
                mainViewModel.setImage("https://cdn-images-1.medium.com/v2/resize:fit:1200/1*Dv4gnBhPF8PtDcH-gjYgEQ.png")
                navController.navigate(DTournaments.route)
            }
        }
    }
}