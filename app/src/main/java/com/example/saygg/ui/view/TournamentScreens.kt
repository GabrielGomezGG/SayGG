package com.example.saygg.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.saygg.R
import com.example.saygg.data.model.Image
import com.example.saygg.data.model.Tournament
import com.example.saygg.utils.timeStampToDate

@Composable
fun TournamentView(images: List<Image>, title: String, starAt: Int, endAt: Int) {
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

    Card(
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 16.dp
        )
    ) {
        Column(
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(Modifier) {
                SubcomposeAsyncImage(
                    model = image.ifEmpty { imageNoFound },
                    loading = {
                        CircularProgressIndicator()
                    },
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier
                        .width(100.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Column(
                    Modifier
                        .weight(2f)
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 8.dp)
                ) {
                    Text(
                        text = title,
                        Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Text(
                        text = date,
                        Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}


@Composable
fun TournamentsView(tournamentList: List<Tournament>, modifier : Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(tournamentList) {
            TournamentView(
                images = it.images,
                endAt = it.endAt,
                starAt = it.startAt,
                title = it.name
            )
        }
    }
}