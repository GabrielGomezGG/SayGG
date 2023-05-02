package com.example.saygg.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.saygg.ui.viewmodel.MainViewModel

@Composable
fun MainScreen(mainViewModel: MainViewModel) {
    val tournaments by mainViewModel.tournamentList.observeAsState()
}