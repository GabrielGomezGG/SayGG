package com.example.saygg.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saygg.TournamentsByCountryQuery
import com.example.saygg.data.TournamentRepository
import com.example.saygg.data.model.Tournament
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val tournamentRepository: TournamentRepository
) : ViewModel() {
    private val _tournamentList = MutableLiveData<List<Tournament>>();
    val tournamentList: LiveData<List<Tournament>> = _tournamentList

    init {
        getTournamentList()
    }

    private fun getTournamentList() {
        try {
            viewModelScope.launch {
                _tournamentList.value = tournamentRepository.getTournaments("AR", 30)
                _tournamentList.value!!.forEach {
                    Log.d("titi", it.name)
                }
            }
        } catch (e: Exception) {
            Log.d("LOL", "ERROROROROR")
        }
    }
}