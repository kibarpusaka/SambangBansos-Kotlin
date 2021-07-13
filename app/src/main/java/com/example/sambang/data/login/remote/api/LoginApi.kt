package com.example.sambang.data.login.remote.api

import com.example.sambang.data.login.remote.dto.LoginRequest
import com.example.sambang.data.login.remote.dto.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("api/api-token-auth/")
    suspend fun login(@Body loginRequest: LoginRequest) : Response<LoginResponse>
}