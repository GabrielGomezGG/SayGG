package com.example.saygg.tournament.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.saygg.main.ui.MainNavigationDrawerItems
import com.example.saygg.main.ui.MainTopAppBar
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TournamentMainScreen(
    tournament: TournamentUiState?,
    titleTop : String,
    imageTop : String,
    onClickBackHandler: () -> Unit
) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(Modifier.width(80.dp)) {
                MainNavigationDrawerItems()
            }
        }
    ){
        Scaffold(
            topBar = {
                MainTopAppBar(
                    titleTop,
                    imageTop
                ) {
                    coroutineScope.launch {
                        drawerState.open()
                    }
                }
            },
        ) {
            TournamentView(
                tournament,
                modifier = Modifier.padding(it)
            ){
                onClickBackHandler()
            }
        }
    }
}