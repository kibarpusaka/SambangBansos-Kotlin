package com.example.sambang.data.login

import com.example.sambang.data.login.remote.api.LoginApi
import com.example.sambang.data.login.remote.dto.LoginRequest
import com.example.sambang.domain.common.base.BaseResult
import com.example.sambang.domain.common.base.Failure
import com.example.sambang.domain.login.entity.LoginEntity
import com.example.sambang.domain.login.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginRepositoryImpl constructor(
    private val loginApi: LoginApi
) : LoginRepository {
    override suspend fun login(loginRequest: LoginRequest): Flow<BaseResult<LoginEntity, Failure>> {
        return flow {
            val response = loginApi.login(loginRequest)
            if(response.isSuccessful){
                val body = response.body()!!
                val loginEntity = LoginEntity(body.user.userId, body.user.username, body.user.email, body.token!!)
                emit(BaseResult.Success(loginEntity))
            }else{
                val message = "Tidak dapat login dengan kredensial yang diberikan"
                emit(BaseResult.Error(Failure(response.code(), message)))
            }
        }
    }
}