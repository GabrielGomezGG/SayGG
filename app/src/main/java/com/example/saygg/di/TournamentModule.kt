package com.example.saygg.di

import com.apollographql.apollo3.ApolloClient
import com.example.saygg.tournament.data.ITournamentRepository
import com.example.saygg.tournament.data.TournamentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class TournamentModule {

    @Singleton
    @Provides
    fun provideTournamentRepository(apolloClient : ApolloClient):ITournamentRepository{
        return TournamentRepository(apolloClient)
    }

}