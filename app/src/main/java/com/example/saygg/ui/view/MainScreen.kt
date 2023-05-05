package com.example.saygg.ui.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.saygg.R
import com.example.saygg.ui.viewmodel.TournamentUiState
import com.example.saygg.ui.viewmodel.TournamentViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(tournamentViewModel: TournamentViewModel) {

    val tournaments by tournamentViewModel.tournamentList.observeAsState()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)

    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                Modifier.width(80.dp)
            ){
                NavigationDrawerItem(
                    icon = {
                        Image(
                            painter = painterResource(id = R.drawable.startgglogo),
                            contentDescription = null,
                        )
                    },
                    label = {
                    },
                    selected = false,
                    onClick = { /*TODO*/ },
                )
                Divider(thickness = 3.dp)
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            Modifier.fillMaxSize()
                        )
                    },
                    label = {
                    },
                    selected = false,
                    onClick = { /*TODO*/ },
                )
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = null,
                            Modifier.fillMaxSize()
                        )
                    },
                    label = {
                    },
                    selected = false,
                    onClick = { /*TODO*/ },
                )
            }
        },
    ) {
        Scaffold(
            topBar = {
                MainTopAppBar(title = "Tournaments") {
                    coroutineScope.launch {
                        drawerState.open()
                    }
                }
            },

            ) {

            when (tournaments) {
                is TournamentUiState.Error -> {
                    Text(text = (tournaments as TournamentUiState.Error).message)
                }

                is TournamentUiState.Loading -> {

                }

                is TournamentUiState.Success -> {
                    TournamentsView(tournamentList = (tournaments as TournamentUiState.Success).tournamentList)
                }

                else -> {}
            }

        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(title: String, onMenuPressed: () -> Unit) {
    TopAppBar(
        title = {
            Box(modifier = Modifier.padding(8.dp)) {
                IconButton(
                    onClick = { /*TODO*/ },
                    Modifier.size(35.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.startgglogo),
                        contentDescription = null,
                    )
                }
                Text(
                    text = title,
                    Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        },
        navigationIcon = {
            IconButton(
                onClick = { onMenuPressed() }
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = null,
                    Modifier.size(80.dp)
                )
            }
        }
    )
}

