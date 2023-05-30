package com.example.saygg.tournament.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.saygg.R
import com.example.saygg.events.data.Event
import com.example.saygg.events.ui.EventThumbnail
import com.example.saygg.profile.data.Player
import com.example.saygg.profile.ui.ProfileThumbnail
import com.example.saygg.tournament.data.model.Tournament
import com.example.saygg.utils.GenericBox
import com.example.saygg.utils.IconContact
import com.example.saygg.utils.getImageUrlByType
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun TournamentView(
    tournamentValue: TournamentUiState?,
    modifier: Modifier = Modifier,
    onClickBackHandler: () -> Unit
) {
    when (tournamentValue) {
        is TournamentUiState.Success<*> -> {

            BackHandler {
                onClickBackHandler()
            }

            val tournament = tournamentValue.values as Tournament

            LazyColumn(modifier = modifier) {
                item {
                    if (getImageUrlByType(tournament.images, "banner").isNotEmpty()) {
                        SubcomposeAsyncImage(
                            model = getImageUrlByType(tournament.images, "banner"),
                            loading = {
                                GenericBox {
                                    CircularProgressIndicator()
                                }
                            },
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                        )
                    }
                    TournamentThumbnail(
                        images = getImageUrlByType(tournament.images, "profile"),
                        title = tournament.name,
                        starAt = tournament.startAt,
                        endAt = tournament.endAt,
                        attenders = tournament.numAttendees,
                        venueAddress = tournament.venueAddress,
                        contact = {
                            Row(Modifier.fillMaxWidth()) {
                                IconContact(typeContact = tournament.primaryContactType)
                                Spacer(modifier = Modifier.size(4.dp))
                                Text(
                                    text = tournament.primaryContact,
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 2.dp),
                                    style = MaterialTheme.typography.labelMedium,
                                )
                            }
                        }
                    )
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(6.dp)
                    ) {

                        MySection(stringResource(id = R.string.tournament_info))

                        //Location
                        MySection(stringResource(R.string.location))
                        LocationTournament(
                            lat = tournament.latitude,
                            lng = tournament.longitude,
                            title = tournament.venueAddress,
                            tournamentTitle = tournament.name
                        )

                        //Event
                        MySection(stringResource(R.string.events))
                        EventTournamentView(tournament.events)

                        //Attendees
                        MySection(stringResource(R.string.attenders))
                        AttendeesTournamentView(
                            players = tournament.players,
                            numAttendees = tournament.numAttendees,
                        )

                        //Contact info
                        MySection(stringResource(R.string.contact_info))
                        ContactInfo(
                            primaryContact = tournament.primaryContactType
                        )

                        //Owner
                        MySection(stringResource(R.string.owner))
                        OwnerTournamentView(owner = tournament.owner)

                        //Rules
                        MySection(stringResource(R.string.rules))
                        Text(text = tournament.rules)
                    }
                }
            }
        }

        is TournamentUiState.Error -> {
            GenericBox {
                Text(text = tournamentValue.message)
            }
        }

        TournamentUiState.Loading -> {
            GenericBox {
                CircularProgressIndicator()
            }
        }

        null -> TODO()
    }


}

@Composable
private fun MySection(title: String) {
    Divider(thickness = 2.dp, modifier = Modifier.padding(vertical = 6.dp))
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun LocationTournament(
    lat: Double,
    lng: Double,
    modifier: Modifier = Modifier,
    title: String = "",
    tournamentTitle: String = ""
) {
    val marker = LatLng(lat, lng)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(marker, 17f)
    }
    GoogleMap(
        modifier = modifier.height(300.dp),
        cameraPositionState = cameraPositionState,
    ) {
        Marker(
            state = rememberMarkerState(position = marker),
            title = title,
            snippet = tournamentTitle,
            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
        )
    }
}

@Composable
private fun EventTournamentView(
    events : List<Event>
) {
    repeat(if(events.size > 4) 5 else events.size){
        EventThumbnail(
            gameImage = getImageUrlByType(events[it].videoGame.images, "primary"),
            startAt = events[it].startAt,
            eventName = events[it].name,
            gameName = events[it].videoGame.name,
            modifier = Modifier.padding(4.dp)
        )
    }
    TextButton(
        onClick = { /*TODO*/ }
    ) {
        Text(
            text = stringResource(R.string.view_all_events),
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
private fun AttendeesTournamentView(
    players: List<Player>,
    numAttendees: Int,
    modifier: Modifier = Modifier,
) {
    Card {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(140.dp),
            userScrollEnabled = false,
            modifier = modifier.heightIn(max = 700.dp),
        ) {
            items(if (numAttendees >= 12) 12 else numAttendees) {
                ProfileThumbnail(
                    image = getImageUrlByType(players[it].image, "profile"),
                    prefix = players[it].prefix,
                    gamerTag = players[it].gamerTag ?: "",
                    name = players[it].name ?: "",
                    socialNetworks = players[it].socialNetworks,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(
                text = stringResource(R.string.view_all_attendees, numAttendees),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun OwnerTournamentView(
    owner: Player
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        ProfileThumbnail(
            image = getImageUrlByType(owner.image, "profile"),
            prefix = owner.prefix,
            gamerTag = owner.gamerTag ?: "",
            name = owner.name ?: "",
            socialNetworks = owner.socialNetworks,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
private fun ContactInfo(
    primaryContact: String,
) {
    Column {
        IconContact(
            typeContact = primaryContact,
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.CenterHorizontally),
            tint = Color(0xFF397ea8)
        )
        Text(
            text = primaryContact,
            color = Color(0xFF397ea8),
            fontSize = 20.sp
        )
    }
}