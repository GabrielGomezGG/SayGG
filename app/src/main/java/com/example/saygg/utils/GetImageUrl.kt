package com.example.saygg.utils

import com.example.saygg.tournament.data.model.Image

fun getImageUrlByType(images: List<Image>?, type: String): String {
    images?.map {
        if (it.type == type) {
            return it.url
        }
    }
    return ""
}