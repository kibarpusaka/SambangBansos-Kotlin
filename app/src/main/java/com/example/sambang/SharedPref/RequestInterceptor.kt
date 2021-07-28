package com.example.sambang.SharedPref

import okhttp3.Interceptor
import okhttp3.Response

//class RequestInterceptor(private val prefs: SessionManager) : Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val requestBuilder = chain.request().newBuilder()
//        // If token has been saved, add it to the request
//        prefs.fetchAuthToken()?.let {
//            requestBuilder.addHeader("Authorization", "token $it")
//        }
//        return chain.proceed(requestBuilder.build())
//    }
//}


class RequestInterceptor(private val prefs: SessionManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = prefs.getUserToken()
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", token)
            .build()
        return chain.proceed(newRequest)
    }
}