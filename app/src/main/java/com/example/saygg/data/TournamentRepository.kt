package com.example.saygg.data

import android.util.Log
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
                it?.toTournament() ?: Tournament("",0,0,"")
            } ?: emptyList()
            TournamentUiState.Success(tournaments)
        }catch (e : ApolloException){
            TournamentUiState.Error(e.message.toString())
        }
//        try {
//            val tournamentsApi = apolloClient.query(TournamentsByCountryQuery(countryCode,perPage)).execute()
//            val tournaments = tournamentsApi.data?.tournaments?.nodes?.map {
//                it?.toTournament() ?: Tournament("",0,0,"")
//            } ?: emptyList()
//            return tournaments
//        }catch (e:Exception){
//            return emptyList()
//        }
    }
}