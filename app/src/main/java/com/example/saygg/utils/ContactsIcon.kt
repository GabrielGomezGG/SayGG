package com.example.saygg.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.toLowerCase
import com.example.saygg.R

@Composable
fun IconContact(
    typeContact: String,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current,
) {
    when (typeContact.lowercase()) {
        "discord" -> {
            Icon(
                painter = painterResource(id = R.drawable.discord),
                contentDescription = null,
                modifier = modifier,
                tint = tint
            )
        }
        "email" -> {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = null,
                modifier = modifier,
                tint = tint
            )
        }
        "twitter" -> {
            Icon(
                painter = painterResource(id = R.drawable.twitter_icon),
                contentDescription = null,
                modifier = modifier,
                tint = tint
            )
        }
        "twitch" -> {
            Icon(
                painter = painterResource(id = R.drawable.twitch_icon),
                contentDescription = null,
                modifier = modifier,
                tint = tint
            )
        }
    }
}