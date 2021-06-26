package com.example.sambang.Api

import com.example.sambang.BuildConfig
import com.google.gson.GsonBuilder
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

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://04f0a4bca50c.ngrok.io")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun service() = getRetrofit().create(ApiSambang::class.java)
//    fun getSambangClientInstance() : Retrofit {
//        val gson = GsonBuilder().setLenient().create()
//        return Retrofit.Builder()
//            .baseUrl("http://9379d98d6ac1.ngrok.io/")
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()
//    }
}