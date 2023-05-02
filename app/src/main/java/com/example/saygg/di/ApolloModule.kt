package com.example.saygg.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.example.saygg.utils.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApolloModule {

    private val token = "d90e76f84cef535f80af3f29b937297c"
    private val url = "https://api.start.gg/gql/alpha"

    @Singleton
    @Provides
    fun provideOkHttpClientWithInterceptor():OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(token))
            .build()
    }

    @Singleton
    @Provides
    fun provideApolloClient(okHttpClient: OkHttpClient):ApolloClient{
        return ApolloClient.Builder()
            .serverUrl(url)
            .okHttpClient(okHttpClient)
            .build()
    }

}