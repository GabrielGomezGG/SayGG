package com.example.saygg.profile.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.saygg.profile.data.SocialNetwork
import com.example.saygg.ui.theme.SayGGTheme
import com.example.saygg.utils.IconContact

@Composable
fun ProfileThumbnail(
    modifier: Modifier = Modifier,
    image: String,
    prefix: String?,
    gamerTag: String,
    name: String?,
    authorizations: List<SocialNetwork>,
) {
    Row(modifier = modifier) {
        AsyncImage(
            model = image,
            contentDescription = "Profile Image",
            modifier = Modifier
                .padding(end = 8.dp)
                .clip(CircleShape)
                .size(50.dp)
                .align(Alignment.CenterVertically)
            ,
            contentScale = ContentScale.FillWidth
        )
        Column(Modifier.fillMaxWidth().align(Alignment.CenterVertically)) {
            Row(Modifier.fillMaxWidth()) {
                //Prefix
                if (!prefix.isNullOrBlank())
                    Text(
                        text = "$prefix ",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray
                    )
                //GamerTag
                Text(
                    text = gamerTag,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold
                )
            }

            //Name
            if (!name.isNullOrBlank()){
                Text(
                    text = name,
                    style = MaterialTheme.typography.labelSmall,
                )
            }
            //Social Network
            Row(Modifier.fillMaxWidth().padding(top = 2.dp)) {
                authorizations.forEach {
                    IconContact(typeContact = it.type, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.size(8.dp))
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ProfileThumbnailPrev() {

    //2 list of Social Network
    val authorizations = listOf(
        SocialNetwork("discord", "Saygg#0001"),
        SocialNetwork("email", "asdsad"),
        SocialNetwork("twitter", "ASdasd"),
        SocialNetwork("twitch", "asdasd"),
    )
    SayGGTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Row(Modifier.fillMaxWidth().padding(8.dp)) {
                ProfileThumbnail(
                    image = "https://images.start.gg/images/user/1637510/image-f4396f966669a24d781994992a85d531.jpg?ehk=iple%2BWLAZMAb8qO7MCvJRvhWLkExUkriT9KLk7D9iqQ%3D&ehkOptimized=Snd5SX4Pc4Bl03nxCn97syAOLz2532spdz3VEPokVic%3D",
                    prefix = "",
                    gamerTag = "Saygg",
                    name = "",
                    modifier = Modifier.weight(1f),
                    authorizations = authorizations
                )
                ProfileThumbnail(
                    image = "https://images.start.gg/images/user/1637510/image-f4396f966669a24d781994992a85d531.jpg?ehk=iple%2BWLAZMAb8qO7MCvJRvhWLkExUkriT9KLk7D9iqQ%3D&ehkOptimized=Snd5SX4Pc4Bl03nxCn97syAOLz2532spdz3VEPokVic%3D",
                    prefix = "VAL |",
                    gamerTag = "ComanDanteTy",
                    name = "Smite Smite",
                    modifier = Modifier.weight(1f),
                    authorizations = authorizations
                )
            }
        }
    }
}