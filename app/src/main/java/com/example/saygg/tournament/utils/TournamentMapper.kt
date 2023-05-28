package com.example.saygg.tournament.utils

import com.example.saygg.TournamentByIdQuery
import com.example.saygg.TournamentsByCountryQuery
import com.example.saygg.profile.data.Player
import com.example.saygg.profile.data.SocialNetwork
import com.example.saygg.tournament.data.model.Image
import com.example.saygg.tournament.data.model.Tournament

fun TournamentsByCountryQuery.Node.toTournament(): Tournament {
    return Tournament(
        images = images!!.map {
            Image(
                type = it?.type ?: "",
                url = it?.url ?: ""
            )
        },
        name = name ?: "",
        venueAddress = venueAddress ?: "",
        startAt = (startAt) as Int,
        endAt = (endAt) as Int,
        numAttendees = numAttendees ?: 0,
        id = id ?: "",
    )
}

fun TournamentByIdQuery.Tournament.toTournament(): Tournament {
    return Tournament(
        images = images!!.map {
            Image(
                type = it?.type ?: "",
                url = it?.url ?: ""
            )
        },
        name = name ?: "",
        venueAddress = venueAddress ?: "",
        startAt = (startAt) as Int,
        endAt = (endAt) as Int,
        numAttendees = numAttendees ?: 0,
        primaryContact = primaryContact ?: "",
        primaryContactType = primaryContactType ?: "",
        rules = rules ?: "",
        latitude = lat ?: 0.0,
        longitude = lng ?: 0.0
    )
}

fun TournamentByIdQuery.Tournament.toPlayer(): List<Player> {
    val playerList = participants?.nodes?.map {
        Player(
            gamerTag = it?.player?.gamerTag,
            name = it?.player?.user?.name,
            prefix = it?.player?.prefix,
            image = it?.player?.user?.images?.first()?.url,
            socialNetworks = it?.player?.user?.authorizations?.map { sn ->
                SocialNetwork(
                    type = sn?.type?.toString(),
                    externalUsername = sn?.externalUsername.toString()
                )
            }
        )
    }
    return playerList ?: emptyList()
}