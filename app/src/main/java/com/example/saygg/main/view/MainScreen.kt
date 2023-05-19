package com.example.saygg.main.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.saygg.NavigationHost
import com.example.saygg.R
import com.example.saygg.main.viewmodel.MainViewModel
import com.example.saygg.tournament.data.model.Tournament
import com.example.saygg.tournament.viewmodel.TournamentViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    tournamentViewModel: TournamentViewModel,
    mainViewModel: MainViewModel
) {
    val title by mainViewModel.title.observeAsState(stringResource(id = R.string.tournaments))
    val imageTitle by mainViewModel.imageTitle.observeAsState()

    val isTournamentView by tournamentViewModel.isViewTournament.observeAsState(false)

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Row {
                ModalDrawerSheet(
                    Modifier.width(80.dp),
                ) {
                    MainNavigationDrawerItems()
                }
            }
        },
    ) {
        Scaffold(
            topBar = {
                MainTopAppBar(title, imageTitle!!) {
                    coroutineScope.launch {
                        drawerState.open()
                    }
                }
            }
        ) {
            MainContent(
                it,
                tournamentViewModel,
                mainViewModel
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(
    title: String,
    imageTitle : String,
    onMenuPressed: () -> Unit
) {
    TopAppBar(
        title = {
            Row(modifier = Modifier.padding(horizontal = 8.dp)) {
//                IconButton(
//                    onClick = { /*TODO*/ },
//                ) {
                    AsyncImage(
                        model = imageTitle,
                        contentDescription = null,
                        Modifier
                            .size(40.dp)
                            .align(CenterVertically)
                    )
                //}
                Text(
                    text = title,
                    Modifier
                        .fillMaxWidth()
                        .align(CenterVertically),
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
                    Modifier.size(48.dp)
                )
            }
        },
    )
}

@Composable
fun MainContent(
    padding: PaddingValues,
    tournamentViewModel: TournamentViewModel,
    mainViewModel: MainViewModel,
) {
    NavigationHost(
        modifier = Modifier.padding(padding),
        tournamentViewModel,
        mainViewModel
    )
}


@Composable
fun MainNavigationDrawerItems(tournamentItem: List<Tournament> = emptyList()) {

    //Start GG icon
    MainNavigationDrawerItem(
        icon = R.drawable.startgg,
        modifier = Modifier.padding(vertical = 8.dp)
    )

    Divider(thickness = 2.dp, modifier = Modifier.padding(vertical = 8.dp))

    //Search Icon
    MainNavigationDrawerItem(icon = Icons.Default.Search)

    //Notification Icon
    MainNavigationDrawerItem(icon = Icons.Default.Notifications)

    Divider(thickness = 2.dp, modifier = Modifier.padding(vertical = 8.dp))

    //Add Icon
    MainNavigationDrawerItem(icon = Icons.Filled.Add)

    Divider(thickness = 2.dp, modifier = Modifier.padding(vertical = 8.dp))

    //HELP ICON
    MainNavigationDrawerItem(R.drawable.baseline_help_outline_24)

    //language ICON
    MainNavigationDrawerItem(R.drawable.baseline_g_translate_24)

    Divider(thickness = 2.dp, modifier = Modifier.padding(vertical = 8.dp))

    //Profile ICON
    MainNavigationDrawerItem(R.drawable.cat, isProfile = true)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> MainNavigationDrawerItem(
    icon: T,
    modifier: Modifier = Modifier,
    isProfile: Boolean = false,
) {
    val modifierProfile: Modifier = if (isProfile)
        Modifier.clip(CircleShape)
    else Modifier

    NavigationDrawerItem(
        icon = {
            when (icon) {
                is Int -> {
                    Image(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        modifier = modifierProfile,
                    )
                }

                is ImageVector -> {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
        },
        label = {
        },
        selected = false,
        onClick = { /*TODO*/ },
        modifier = modifier
    )
}