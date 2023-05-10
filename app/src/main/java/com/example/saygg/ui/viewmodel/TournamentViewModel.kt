package com.example.saygg.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saygg.data.TournamentRepository
import com.example.saygg.data.model.Tournament
import com.example.saygg.ui.uistate.TournamentUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TournamentViewModel @Inject constructor(
    private val tournamentRepository: TournamentRepository
) : ViewModel() {
    private val _tournamentList = MutableLiveData<TournamentUiState>(TournamentUiState.Loading);
    val tournamentList: LiveData<TournamentUiState> = _tournamentList

    private val _tournament = MutableLiveData<Tournament>();
    val tournament: LiveData<Tournament> = _tournament

    init {
        getTournamentList("AR", 20)
    }

    private fun getTournamentList(countryCode : String, perPage:Int) {
        viewModelScope.launch {
            _tournamentList.value = tournamentRepository.getTournaments(countryCode, perPage)
        }
    }

    fun getTournamentById(id : String){
        viewModelScope.launch {
            _tournament.value = tournamentRepository.getTournamentById(id)
        }
    }
}