package com.example.saygg.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun timeStampToDate(timeStamp: Int): String {
    val date = Date(timeStamp.toLong() * 1000)
    val formatter = SimpleDateFormat("MMMM d yyyy", Locale.ENGLISH)
    return formatter.format(date)
}