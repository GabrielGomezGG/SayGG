package com.example.saygg.profile.data

import com.example.saygg.tournament.data.model.Image

data class Player(
    val gamerTag: String?,
    val name: String?,
    val prefix: String?,
    val image: List<Image>?,
    val socialNetworks: List<SocialNetwork>?,
)
