package com.example.saygg.data.model

data class Tournament(
    val images: List<Image>,
    val name: String,
    val startAt: Int,
    val endAt: Int,
    val numAttendees: Int = 0,
    val events: List<Event>,
    val rules : String,
    val venueAddress : String = ""
)