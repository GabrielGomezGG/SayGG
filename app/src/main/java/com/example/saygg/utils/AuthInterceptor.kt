package com.example.saygg.utils

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val authToken: String
    ) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
            .header("Authorization", "Bearer $authToken")
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}