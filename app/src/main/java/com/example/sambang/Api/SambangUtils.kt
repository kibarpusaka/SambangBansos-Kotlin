package com.example.sambang.Utils

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SambangUtils {
    fun getSambangClientInstance() : Retrofit {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl("http://9379d98d6ac1.ngrok.io/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}