package com.example.saygg.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloException
import com.example.saygg.TournamentsByCountryQuery
import com.example.saygg.data.model.Tournament
import com.example.saygg.ui.viewmodel.TournamentUiState
import com.example.saygg.utils.toTournament
import javax.inject.Inject

class TournamentRepository @Inject constructor(
    private val apolloClient: ApolloClient
){
    suspend fun getTournaments(countryCode : String, perPage : Int): TournamentUiState{
        return try {
            val tournamentsApi = apolloClient.query(TournamentsByCountryQuery(countryCode,perPage)).execute()
            val tournaments = tournamentsApi.data?.tournaments?.nodes?.map {
                it?.toTournament() ?: Tournament(
                    emptyList(),
                    "",
                    0,
                    0,
                    events = emptyList(),
                    rules = ""
                )
            } ?: emptyList()
            TournamentUiState.Success(tournaments)
        }catch (e : ApolloException){
            TournamentUiState.Error(e.message.toString())
        }
    }
}