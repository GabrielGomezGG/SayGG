package com.example.saygg.profile.ui

import androidx.compose.foundation.clickable
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
import com.example.saygg.R
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
    socialNetworks: List<SocialNetwork>?,
) {

    Row(modifier = modifier.clickable {  }) {
        AsyncImage(
            model = image.ifEmpty { R.drawable.generic_user_icon },
            contentDescription = "Profile Image",
            modifier = Modifier
                .padding(end = 8.dp)
                .clip(CircleShape)
                .size(50.dp)
                .align(Alignment.CenterVertically),
            contentScale = ContentScale.Crop
        )

        Column(
            Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
        ) {
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
            if (!name.isNullOrBlank()) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.labelSmall,
                )
            }
            //Social Network
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp)
            ) {
                if (!socialNetworks.isNullOrEmpty()) {
                    socialNetworks.forEach {
                        IconContact(typeContact = it.type!!, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.size(8.dp))
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ProfileThumbnailPrev() {

    val authorizations = listOf(
        SocialNetwork("discord", "Saygg#0001"),
        SocialNetwork("email", "asdsad"),
        SocialNetwork("twitter", "ASdasd"),
        SocialNetwork("twitch", "asdasd"),
    )
    SayGGTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//            Row(
//                Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp)) {
//                ProfileThumbnail(
//                    image = "https://images.start.gg/images/user/1637510/image-f4396f966669a24d781994992a85d531.jpg?ehk=iple%2BWLAZMAb8qO7MCvJRvhWLkExUkriT9KLk7D9iqQ%3D&ehkOptimized=Snd5SX4Pc4Bl03nxCn97syAOLz2532spdz3VEPokVic%3D",
//                    prefix = "",
//                    gamerTag = "Saygg",
//                    name = "",
//                    modifier = Modifier.weight(1f),
//                    socialNetworks = authorizations
//                )
//                ProfileThumbnail(
//                    image = "https://images.start.gg/images/user/661021/image-dba7b4317b120c89c3aad383aef83b43.jpg?ehk=l%2FTHoZ6sYd0fsN6gM1x1b1W20qpSyPKx0m7%2BISYolN0%3D&ehkOptimized=UxEWTfxw8j1dypn0FoUpVuCBM9L6YwnEXYsAQnFqiP0%3D",
//                    prefix = "VAL |",
//                    gamerTag = "ComanDanteTy",
//                    name = "",
//                    modifier = Modifier.weight(1f),
//                    socialNetworks = null
//                )
//            }
//            val numAttendees = 12
//            LazyVerticalGrid(
//                columns = GridCells.Adaptive(140.dp),
//                userScrollEnabled = false,
//                modifier = Modifier.height(400.dp),
//            ) {
//                items(if(numAttendees >= 12) 12 else numAttendees){
//                    ProfileThumbnail(
//                        image = "https://images.start.gg/images/user/661021/image-dba7b4317b120c89c3aad383aef83b43.jpg?ehk=l%2FTHoZ6sYd0fsN6gM1x1b1W20qpSyPKx0m7%2BISYolN0%3D&ehkOptimized=UxEWTfxw8j1dypn0FoUpVuCBM9L6YwnEXYsAQnFqiP0%3D",
//                        prefix = "VAL |",
//                        gamerTag = "ComanDanteTy",
//                        name = "",
//                        socialNetworks = authorizations
//                    )
//                }
//            }
        }
    }
}