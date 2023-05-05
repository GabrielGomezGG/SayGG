package com.example.saygg.ui.view

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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

    var image = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/No-Image-Placeholder.svg/1665px-No-Image-Placeholder.svg.png"
    images.forEach {
        if (it.type == "profile") {
            image = it.url
        }
    }

    Card(
        modifier = Modifier.padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .height(104.dp)
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(Modifier) {
                SubcomposeAsyncImage(
                    model = image,
                    loading = {
                        CircularProgressIndicator()
                    },
                    contentDescription = null,
                    modifier = Modifier.width(100.dp)
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
fun TournamentsView(tournamentList: List<Tournament>) {
    LazyColumn(Modifier.fillMaxSize()) {
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

@Preview
@Composable
fun TournamentsViewPrev() {
    val images = listOf<Image>(Image("https://picsum.photos/200/300", "profile"))
    val tours = listOf<Tournament>(
        Tournament(images, "Titi", 1, 2, 0, emptyList(), "titi"),
        Tournament(images, "Titi", 1, 2, 0, emptyList(), "titi"),
        Tournament(images, "Titi", 1, 2, 0, emptyList(), "titi"),
        Tournament(images, "Titi", 1, 2, 0, emptyList(), "titi"),
    )
    TournamentsView(tours)
}