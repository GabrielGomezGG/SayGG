package com.example.saygg.utils

import com.example.saygg.TournamentsByCountryQuery
import com.example.saygg.data.model.Event
import com.example.saygg.data.model.Image
import com.example.saygg.data.model.Tournament

fun TournamentsByCountryQuery.Node.toTournament() : Tournament{
    return Tournament(
        images = images!!.map { Image(
            type = it?.type ?: "",
            url = it?.url ?: "") },
        name = name ?: "",
        startAt = (startAt) as Int,
        endAt = (endAt) as Int,
        events = events!!.map{ Event(
            name = it?.name ?: "",
            videogame = it?.videogame?.name ?: "",
            videogameImage = it?.videogame?.images.toString()
        )},
        rules = rules ?: "",
        numAttendees = numAttendees ?: 0
    )
}