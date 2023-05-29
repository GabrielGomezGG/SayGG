package com.example.saygg.tournament.data.model

import com.example.saygg.profile.data.Player

data class Tournament(
    val id : String = "",
    val images: List<Image> = emptyList(),
    val name: String = "",
    val startAt: Int = 0,
    val endAt: Int = 0,
    val numAttendees: Int = 0,
    val venueAddress : String = "",
    val primaryContact : String = "",
    val primaryContactType : String = "",
    val rules : String = "",
    val latitude : Double = 0.0,
    val longitude : Double = 0.0,
    val players : List<Player> = emptyList(),
    val owner : Player = Player(
        gamerTag = "",
        name = "",
        prefix = "",
        image = emptyList(),
        socialNetworks = emptyList()
    ),
)