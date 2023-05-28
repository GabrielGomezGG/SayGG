package com.example.saygg.profile.data

import com.example.saygg.tournament.data.model.Image

data class Player(
    val gamerTag: String?,
    val name: String?,
    val prefix: String?,
    val image: List<Image>?,
    val socialNetworks: List<SocialNetwork>?,
) {
    fun getProfileImage(): String {
        image?.map {
            if (it.type == "profile") {
                return it.url
            }
        }
        return ""
    }

    fun getBannerImage() : String{
        image?.map {
            if (it.type == "banner") {
                return it.url
            }
        }
        return ""
    }
}
