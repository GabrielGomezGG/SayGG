package com.example.saygg.ui.view

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.SubcomposeAsyncImage
import com.example.saygg.R
import com.example.saygg.data.model.Image
import com.example.saygg.data.model.Tournament
import com.example.saygg.ui.uistate.TournamentUiState
import com.example.saygg.utils.GenericBox
import com.example.saygg.utils.timeStampToDate

@Composable
fun TournamentView(
    tournamentValue: TournamentUiState?,
    onClickBackHandler: () -> Unit
) {
    when (tournamentValue) {

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

        is TournamentUiState.Success<*> -> {

            BackHandler {
                onClickBackHandler()
            }

            val tournament = tournamentValue.values as Tournament

            var imageBanner = ""

            tournament.images.forEach {
                if (it.type == "banner") {
                    imageBanner = it.url
                }
            }

            Column(Modifier.fillMaxWidth()) {
                if (imageBanner.isNotEmpty()) {
                    SubcomposeAsyncImage(
                        model = imageBanner,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
                Card(
                    modifier = Modifier
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    )
                ) {
                    TournamentThumbnail(
                        images = tournament.images,
                        title = tournament.name,
                        starAt = tournament.startAt,
                        endAt = tournament.endAt,
                        attenders = tournament.numAttendees,
                        venueAddress = tournament.venueAddress,
                        contact = {
                            Row(Modifier.fillMaxWidth()) {
                                if (tournament.primaryContactType == "discord") {
                                    Icon(
                                        painter = painterResource(id = R.drawable.discord),
                                        contentDescription = null
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Default.Email,
                                        contentDescription = null
                                    )
                                }
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
                }
            }
        }

        null -> TODO()
    }


}

@Composable
fun TournamentThumbnail(
    images: List<Image>,
    title: String,
    starAt: Int,
    endAt: Int,
    attenders: Int,
    venueAddress: String,
    contact: @Composable () -> Unit = {}
) {
    val start = timeStampToDate(starAt)
    val end = timeStampToDate(endAt)
    val date = "$start - $end"

    var image = ""
    val imageNoFound = R.drawable.tournament

    images.forEach {
        if (it.type == "profile") {
            image = it.url
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(Modifier) {
            SubcomposeAsyncImage(
                model = image.ifEmpty { imageNoFound },
                loading = {
                    CircularProgressIndicator()
                },
                contentScale = ContentScale.FillWidth,
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
                DataTournament(icon = null, data = title, MaterialTheme.typography.titleMedium)
                DataTournament(icon = Icons.Default.DateRange, data = date)
                DataTournament(icon = Icons.Default.Person, data = "$attenders Attendees")
                DataTournament(icon = Icons.Default.LocationOn, data = venueAddress)
                contact()
            }
        }
    }
}

@Composable
private fun DataTournament(
    icon: ImageVector?,
    data: String,
    style: TextStyle = MaterialTheme.typography.labelMedium
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
    tournaments: TournamentUiState?,
    modifier: Modifier = Modifier,
    navTournamentView: (String, String) -> Unit
) {


    when (tournaments) {
        is TournamentUiState.Error -> {
            GenericBox {
                Text(text = tournaments.message)
            }
        }

        is TournamentUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Center
            ) {
                CircularProgressIndicator()
            }
        }

        is TournamentUiState.Success<*> -> {
            val tournamentList = tournaments.values as List<Tournament>

            val activity = LocalContext.current as? Activity

            var show by remember {
                mutableStateOf(false)
            }

            BackHandler {
                show = true
            }
            MainDialogAlert(
                showDialog = show,
                onDismissButton = { show = false },
                onClickButton = { activity?.finish() }
            )

            LazyColumn(modifier = modifier) {
                items(tournamentList) {
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable { navTournamentView(it.id, it.name) },
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 8.dp
                        )
                    ) {
                        TournamentThumbnail(
                            images = it.images,
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

        null -> {}
    }

}

@Composable
private fun MainDialogAlert(
    showDialog: Boolean,
    onDismissButton: () -> Unit,
    onClickButton: () -> Unit,
) {
    GenericBox {
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { onDismissButton() },
                title = {
                    Text(
                        text = stringResource(R.string.are_you_sure_exit),
                        textAlign = TextAlign.Center
                    )
                },
                confirmButton = {
                    TextButton(onClick = { onClickButton() }) {
                        Text(text = stringResource(R.string.exit), style = MaterialTheme.typography.titleMedium)
                    }

                },
                dismissButton = {
                    TextButton(onClick = { onDismissButton() }) {
                        Text(text = stringResource(R.string.cancel), style = MaterialTheme.typography.titleMedium)
                    }
                }
            )
        }
    }
}