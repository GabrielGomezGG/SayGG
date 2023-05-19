package com.example.saygg.tournament.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saygg.tournament.data.TournamentRepository
import com.example.saygg.tournament.uistate.TournamentUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TournamentViewModel @Inject constructor(
    private val tournamentRepository: TournamentRepository
) : ViewModel() {
    private val _tournamentList = MutableLiveData<TournamentUiState>(TournamentUiState.Loading)
    val tournamentList: LiveData<TournamentUiState> = _tournamentList

    private val _tournament = MutableLiveData<TournamentUiState>(TournamentUiState.Loading)
    val tournament: LiveData<TournamentUiState> = _tournament

    private val _isViewTournament = MutableLiveData(false)
    val isViewTournament : LiveData<Boolean> = _isViewTournament

    init {
        getTournamentList("AR", 20)
    }

    private fun getTournamentList(countryCode : String, perPage:Int) {
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

    fun setIsView(isEnable : Boolean){
        _isViewTournament.value = isEnable
    }
}