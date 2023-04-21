package com.example.saygg

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

class AuthInterceptor(private val authToken: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
            .header("Authorization", "Bearer $authToken")
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(AuthInterceptor("d90e76f84cef535f80af3f29b937297c"))
    .build()

val apolloClient = ApolloClient.Builder()
    .serverUrl("https://api.start.gg/gql/alpha")
    .okHttpClient(okHttpClient)
    .build()