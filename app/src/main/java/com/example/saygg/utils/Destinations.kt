package com.example.saygg.utils

sealed class Destinations(
    val route : String
){
    object DTournaments : Destinations("tournaments")
    object DTournamentView : Destinations("tournamentView")
}
