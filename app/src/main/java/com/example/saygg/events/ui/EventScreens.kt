package com.example.saygg.events.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.saygg.R
import com.example.saygg.tournament.utils.timeStampToDate

@Composable
fun EventThumbnail(
    gameImage : String,
    startAt : Int,
    eventName : String,
    gameName : String,
    modifier : Modifier = Modifier
) {
    Card(modifier = modifier) {
        Row(Modifier.fillMaxWidth().padding(top = 8.dp)) {
            AsyncImage(
                model = gameImage,
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .align(Alignment.CenterVertically)
            )
            Column(Modifier.fillMaxWidth()) {
                //Date start at
                Text(text = timeStampToDate(startAt))
                //Event name
                Text(
                    text = eventName,
                    style = MaterialTheme.typography.titleMedium,
                )
                //Game name
                Text(text = gameName)
            }
        }
        TextButton(
            onClick = { /*TODO*/ }
        ) {
            Text(
                text = stringResource(R.string.view_event),
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

//@Preview
//@Composable
//fun EventThumbnailPrev() {
//    SayGGTheme() {
//        Surface(modifier = Modifier, color = MaterialTheme.colorScheme.background) {
//            Column {
//                EventThumbnail(
//                    gameImage ="https://images.start.gg/images/videogame/36963/image-bf412b4ff869243300ac5d7e34f5769a.jpg?ehk=uKpe7dw6hwZs9gXLgNLurfL4AnOvo0%2FJPahWYajut64%3D&ehkOptimized=JrspjwheDHPvnihbMzwKlgDwjco%2FJ8DRdsHB1pG9FmI%3D" ,
//                    startAt = 1686970800,
//                    eventName = "Dark Winter #KOFXV",
//                    gameName = "The King of Fighters XV"
//                )
//            }
//        }
//    }
//}