package com.example.saygg.tournament.data.model

data class VideoGame(
    val name: String,
    val images: List<Image>,
) {
    fun getPrimaryImage(): String {
        images.map {
            if (it.type == "primary") {
                return it.url
            }
        }
        return ""

    }

    fun getPrimaryQuality() : String{
        images.map {
            if (it.type == "primary-quality") {
                return it.url
            }
        }
        return ""
    }
}