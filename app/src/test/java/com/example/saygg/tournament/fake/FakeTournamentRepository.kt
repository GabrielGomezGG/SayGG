package com.example.saygg.tournament.fake

import com.example.saygg.tournament.data.ITournamentRepository
import com.example.saygg.tournament.ui.TournamentUiState

class FakeTournamentRepository : ITournamentRepository {
    override suspend fun getTournaments(countryCode: String, perPage: Int): TournamentUiState {
        return TournamentUiState.Success(FakeTournamentSource.fakeTournamentList)
    }

    override suspend fun getTournamentById(id: String): TournamentUiState {
        return TournamentUiState.Success(FakeTournamentSource.fakeTournamentList[0])
    }
}