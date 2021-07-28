package com.example.sambang.Api

import com.example.sambang.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SambangUtils {

    private lateinit var service : ApiSambang
    fun getservice(): ApiSambang {
        if (!::service.isInitialized) {
           val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(getInterceptor())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            service = retrofit.create(ApiSambang::class.java)
        }
        return service
    }
    fun getInterceptor(): OkHttpClient {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }
}