package com.example.saygg.tournament.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saygg.tournament.data.ITournamentRepository
import com.example.saygg.tournament.data.TournamentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TournamentViewModel @Inject constructor(
    private val tournamentRepository: ITournamentRepository
) : ViewModel() {
    private val _tournamentList = MutableLiveData<TournamentUiState>(TournamentUiState.Loading)
    val tournamentList: LiveData<TournamentUiState> = _tournamentList

    private val _tournament = MutableLiveData<TournamentUiState>(TournamentUiState.Loading)
    val tournament: LiveData<TournamentUiState> = _tournament

    fun getTournamentList(countryCode : String, perPage:Int) {
        viewModelScope.launch {
            _tournamentList.value = tournamentRepository.getTournaments(countryCode, perPage)
        }
    }

    fun getTournamentById(id : String){
        _tournament.value = TournamentUiState.Loading
        viewModelScope.launch {
            _tournament.value = tournamentRepository.getTournamentById(id)
        }
    }
}