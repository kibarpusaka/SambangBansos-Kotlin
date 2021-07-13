package com.example.sambang.domain.login.repository

import com.example.sambang.data.login.remote.dto.LoginRequest
import com.example.sambang.domain.common.base.BaseResult
import com.example.sambang.domain.common.base.Failure
import com.example.sambang.domain.login.entity.LoginEntity
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun login(loginRequest: LoginRequest) : Flow<BaseResult<LoginEntity, Failure>>
}