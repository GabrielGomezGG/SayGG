package com.example.saygg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.saygg.main.ui.MainViewModel
import com.example.saygg.tournament.ui.TournamentViewModel
import com.example.saygg.ui.theme.SayGGTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val tournamentViewModel : TournamentViewModel by viewModels()
    private val mainViewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tournamentViewModel.getTournamentList("AR", 20)
        installSplashScreen()

        setContent {
            SayGGTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    NavigationHost(
                        tournamentViewModel = tournamentViewModel,
                        mainViewModel = mainViewModel
                    )
                }
            }
        }
    }
}