package com.example.saygg.tournament.data.model

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
    val rules : String = ""
)