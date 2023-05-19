package com.example.saygg.tournament.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloException
import com.example.saygg.TournamentByIdQuery
import com.example.saygg.TournamentsByCountryQuery
import com.example.saygg.tournament.data.model.Tournament
import com.example.saygg.tournament.ui.TournamentUiState
import com.example.saygg.tournament.utils.toTournament
import javax.inject.Inject

class TournamentRepository @Inject constructor(
    private val apolloClient: ApolloClient
){
    suspend fun getTournaments(countryCode : String, perPage : Int): TournamentUiState {
        return try {
            val tournamentsApi = apolloClient.query(TournamentsByCountryQuery(countryCode,perPage)).execute()
            val tournamentByCountries = tournamentsApi.data?.tournaments?.nodes?.map {
                it?.toTournament() ?: Tournament()
            } ?: emptyList()
            TournamentUiState.Success(tournamentByCountries)
        }catch (e : ApolloException){
            TournamentUiState.Error(e.message.toString())
        }
    }

    suspend fun getTournamentById(id : String): TournamentUiState {
        return try{
            val tournamentApi = apolloClient.query(TournamentByIdQuery(id)).execute()
            val tournamentById = tournamentApi.data?.tournament?.toTournament()
            TournamentUiState.Success(tournamentById ?: Tournament())
        }catch (e : ApolloException){
            return TournamentUiState.Error(e.message.toString())
        }
    }
}