package com.example.sambang.Api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SambangUtils {

    fun getInterceptor(): OkHttpClient {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .build()
    }
    private lateinit var service: ApiSambang

    fun getservice(): ApiSambang {
        if (!::service.isInitialized) {
           val retrofit = Retrofit.Builder()
                .baseUrl("http://42579faba702.ngrok.io")
                .client(getInterceptor())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            service = retrofit.create(ApiSambang::class.java)
        }
        return service
    }

}