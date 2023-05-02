package com.example.saygg.utils

import com.example.saygg.TournamentsByCountryQuery
import com.example.saygg.data.model.Tournament

fun TournamentsByCountryQuery.Node.toTournament() : Tournament{
    return Tournament(
        name = name ?: "",
        startAt = (startAt) as Int,
        endAt = (endAt) as Int,
        rules = rules ?: "")
}