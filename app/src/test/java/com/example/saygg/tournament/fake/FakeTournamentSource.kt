package com.example.saygg.tournament.fake

import com.example.saygg.tournament.data.model.Tournament

object FakeTournamentSource {

    val fakeTournamentList = listOf(
        Tournament(id = "1", name = "Tournament 1"),
        Tournament(id = "2", name = "Tournament 2"),
        Tournament(id = "3", name = "Tournament 3"),
        Tournament(id = "4", name = "Tournament 4"),
    )
}