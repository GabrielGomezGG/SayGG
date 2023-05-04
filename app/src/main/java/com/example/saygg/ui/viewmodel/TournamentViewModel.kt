package com.example.saygg.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saygg.data.TournamentRepository
import com.example.saygg.data.model.Tournament
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

sealed interface TournamentUiState {
    data class Success(val todos: List<Tournament>) : TournamentUiState
    object Loading : TournamentUiState
    data class Error(val message: String) : TournamentUiState
}

@HiltViewModel
class TournamentViewModel @Inject constructor(
    private val tournamentRepository: TournamentRepository
) : ViewModel() {
    private val _tournamentList = MutableLiveData<TournamentUiState>(TournamentUiState.Loading);
    val tournamentList: LiveData<TournamentUiState> = _tournamentList

    init {
        getTournamentList("AR", 20)

        val timestamp = 1620117580L
        val date = Date(timestamp * 1000)

        val formatter = SimpleDateFormat("MMMM d yyyy", Locale.ENGLISH)
        val formattedDate = formatter.format(date)
    }

    private fun getTournamentList(countryCode : String, perPage:Int) {
        viewModelScope.launch {
            _tournamentList.value = tournamentRepository.getTournaments(countryCode, perPage)
        }


    }
}