package com.example.saygg.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.saygg.R
import com.example.saygg.data.model.Tournament
import com.example.saygg.utils.timeStampToDate

@Composable
fun TournamentView(
    imageProfile: String,
    imageBanner: String,
    title: String,
    starAt: Int,
    endAt: Int,
    attenders: Int,
    venueAddress : String
) {

    Column {
        if(imageBanner.isNotEmpty()){
            SubcomposeAsyncImage(
                model = imageBanner,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
            )
        }
        TournamentThumbnail(
            imageProfile = imageProfile,
            title = title,
            starAt = starAt,
            endAt = endAt,
            attenders = attenders,
            venueAddress = venueAddress
        )
    }
}

@Composable
fun TournamentThumbnail(
    imageProfile: String,
    title: String,
    starAt: Int,
    endAt: Int,
    attenders: Int,
    venueAddress : String,
) {
    val start = timeStampToDate(starAt)
    val end = timeStampToDate(endAt)
    val date = "$start - $end"

    val imageNoFound = R.drawable.tournament

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(Modifier) {
            SubcomposeAsyncImage(
                model = imageProfile.ifEmpty { imageNoFound },
                loading = {
                    CircularProgressIndicator()
                },
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .align(CenterVertically)
            )
            Column(
                Modifier
                    .weight(2f)
                    .align(CenterVertically)
                    .padding(horizontal = 8.dp)
            ) {
                DataTournament(icon = null, data = title,MaterialTheme.typography.titleMedium)
                DataTournament(icon = Icons.Default.DateRange, data = date)
                DataTournament(icon = Icons.Default.Person, data = "$attenders Attendees")
                DataTournament(icon = Icons.Default.LocationOn, data = venueAddress)
            }
        }
    }
}

@Composable
fun DataTournament(
    icon: ImageVector?,
    data: String,
    style : TextStyle = MaterialTheme.typography.labelMedium
) {
    Row(Modifier.fillMaxWidth()) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = data,
            Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp),
            style = style,
        )
    }
}


@Composable
fun TournamentsThumbnail(
    tournamentList: List<Tournament>,
    modifier: Modifier = Modifier,
    navTournamentView : () -> Unit
) {
    var imageProfile = ""
    var imageBanner = ""


    LazyColumn(modifier = modifier) {
        items(tournamentList) {
            it.images.forEach { image ->
                imageProfile = ""
                if (image.type == "profile") {
                    imageProfile = image.url
                }
                if(image.type == "banner"){
                    imageBanner = image.url
                }
            }
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { navTournamentView() },
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {
                TournamentThumbnail(
                    imageProfile = imageProfile,
                    endAt = it.endAt,
                    starAt = it.startAt,
                    title = it.name,
                    attenders = it.numAttendees,
                    venueAddress = it.venueAddress
                )
            }
        }
    }
}