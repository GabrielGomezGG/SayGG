package com.example.saygg.tournament.ui

import android.content.SharedPreferences
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
    private val tournamentRepository: ITournamentRepository,
    private val sharedPref : SharedPreferences,
    private val editor : SharedPreferences.Editor
) : ViewModel() {

    private val _tournamentList = MutableLiveData<TournamentUiState>(TournamentUiState.Loading)
    val tournamentList: LiveData<TournamentUiState> = _tournamentList

    private val _tournament = MutableLiveData<TournamentUiState>(TournamentUiState.Loading)
    val tournament: LiveData<TournamentUiState> = _tournament

    private val _country = MutableLiveData<String>()
    val country: LiveData<String> = _country

    fun getTournamentList(
        perPage:Int
    ) {
        viewModelScope.launch {
            _tournamentList.value = tournamentRepository.getTournaments(_country.value ?: "AR", perPage)
        }
    }

    fun getTournamentById(id : String){
        _tournament.value = TournamentUiState.Loading
        viewModelScope.launch {
            _tournament.value = tournamentRepository.getTournamentById(id)
        }
    }

    fun savePrefCountry(countryCode : String){
        editor.apply{
            putString("country", countryCode)
            apply()
        }
        _country.value = sharedPref.getString("country", "AR")
    }
}