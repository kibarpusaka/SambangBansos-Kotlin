package com.example.sambang.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sambang.data.login.remote.dto.LoginRequest
import com.example.sambang.domain.common.base.BaseResult
import com.example.sambang.domain.login.entity.LoginEntity
import com.example.sambang.domain.login.usecase.LoginUseCase
import com.example.sambang.infra.SharedPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val sharedPref: SharedPrefs
) : ViewModel(){
    private val _state = MutableStateFlow<LoginActivityState>(LoginActivityState.Init)
    val state : StateFlow<LoginActivityState> get() = _state

    private fun setLoading(b : Boolean){
        _state.value = LoginActivityState.IsLoading(b)
    }

    private fun showToast(message: String){
        _state.value = LoginActivityState.ShowToast(message)
    }

    private fun onSuccessLogin(loginEntity: LoginEntity){
        sharedPref.saveUserData(loginEntity.username, loginEntity.email,loginEntity.token)
    }

    private fun successLogin(loginEntity: LoginEntity){
        _state.value = LoginActivityState.OnSuccessLogin(loginEntity)
    }

    fun isLoggedIn() = sharedPref.getToken().isNotEmpty()

    fun login(loginRequest: LoginRequest){
        viewModelScope.launch {
            loginUseCase.invoke(loginRequest)
                .onStart { setLoading(true) }
                .catch { e ->
                    setLoading(false)
                    showToast(e.message.toString())
                }
                .collect { res ->
                    setLoading(false)
                    when(res){
                        is BaseResult.Success -> {
                            onSuccessLogin(res.data)
                            successLogin(res.data)
                        }
                        is BaseResult.Error -> {
                            val msg = "${res.e.message} [${res.e.code}]"
                            showToast(msg)
                        }
                    }
                }
        }
    }

}


sealed class LoginActivityState(){
    object Init : LoginActivityState()
    data class IsLoading(val isLoading : Boolean) : LoginActivityState()
    data class ShowToast(val message : String) : LoginActivityState()
    data class OnSuccessLogin(val loginEntity: LoginEntity) : LoginActivityState()
}