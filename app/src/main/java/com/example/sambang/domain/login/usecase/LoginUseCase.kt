package com.example.sambang.domain.login.usecase

import com.example.sambang.data.login.remote.dto.LoginRequest
import com.example.sambang.domain.common.base.BaseResult
import com.example.sambang.domain.common.base.Failure
import com.example.sambang.domain.login.entity.LoginEntity
import com.example.sambang.domain.login.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {
    suspend fun invoke(loginRequest: LoginRequest) : Flow<BaseResult<LoginEntity, Failure>>{
        return loginRepository.login(loginRequest)
    }
}